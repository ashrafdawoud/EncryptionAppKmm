package com.BFCAI.encryptionapp.android.Presentation.Navigation.MyFileScreen

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Interactors.UserFileRepository.UserFileRepository
import com.BFCAI.encryptionapp.Interactors.UserFileRepository.UserFileRepositoryImp
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesEvent
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.GenericMessageInfoQueueUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFilesViewModel @Inject constructor(
    private val userFileRepositoryImp: UserFileRepositoryImp,
    private val context: Context
) : ViewModel() {
    val state: MutableState<UserFilesState> = mutableStateOf(UserFilesState())

    init {
        getAllUserFiles(
            context.getSharedPreferences(
                PublicData.GENERAL_PREF,
                AppCompatActivity.MODE_PRIVATE
            ).getString("User_Id", "")!!
        )
    }

    fun onEventTrigger(event: UserFilesEvent) {
        when (event) {
            is UserFilesEvent.cardIsClicked -> {
                cardClicked(event.cardClicked)
            }
            is UserFilesEvent.refreshListner -> {
                getAllUserFiles(event.userId)
            }
            is UserFilesEvent.deleteFile -> {
                state.value = state.value.copy(cardisClicked = true)
                deleteUserFile(event.objectId)
            }
        }
    }

    fun cardClicked(statevalue: Boolean) {
        state.value = state.value.copy(cardisClicked = statevalue)
    }

    fun getAllUserFiles(userId: String) {
        viewModelScope.launch {
            userFileRepositoryImp.getAllFiles(userId)
                .onEach {
                    it.isLoading?.let {
                        state.value = state.value.copy(isloading = it)
                    }
                    it.data?.let {
                        state.value = state.value.copy(data = it)
                        Log.e("userviewmodelstate", state.value.isloading.toString())
                    }
                    it.message?.let {
                        appendToMessageQueue(it)
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun deleteUserFile(objectId: String) {
        viewModelScope.launch {
            userFileRepositoryImp.deleteFile(objectId)
                .onEach {
                    it.data?.let {
                        state.value = state.value.copy(cardisClicked = false)
                        getAllUserFiles(
                            context.getSharedPreferences(
                                PublicData.GENERAL_PREF,
                                AppCompatActivity.MODE_PRIVATE
                            ).getString("User_Id", "")!!
                        )
                    }
                    it.message?.let {
                        appendToMessageQueue(it)
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder) {
        if (!GenericMessageInfoQueueUtil().doesMessageIsAlreadyExistOnQueue(
                state.value.queue,
                messageInfo.build()
            )
        ) {
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }

    }
}