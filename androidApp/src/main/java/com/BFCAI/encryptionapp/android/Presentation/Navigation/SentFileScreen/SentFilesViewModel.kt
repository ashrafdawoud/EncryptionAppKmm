package com.BFCAI.encryptionapp.android.Presentation.Navigation.SentFileScreen

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesModel
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Interactors.SharedFilesRepository.SharedFilesRepositoryImp
import com.BFCAI.encryptionapp.Presentation.SharedFilesScreen.SharedFilesEvent
import com.BFCAI.encryptionapp.Presentation.SharedFilesScreen.SharedFilesState
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
class SentFilesViewModel @Inject constructor(
    val sharedFilesRepositoryImp: SharedFilesRepositoryImp,
    val context:Context
) :ViewModel(){
    val state: MutableState<SharedFilesState> = mutableStateOf(SharedFilesState())

    init {
        getAllSharedFiles(
            context.getSharedPreferences(
                PublicData.GENERAL_PREF,
                AppCompatActivity.MODE_PRIVATE
            ).getString("User_Id", "")!!
        )
    }

    fun onEventTrigger(event: SharedFilesEvent) {
        when (event) {
            is SharedFilesEvent.cardIsClicked -> {
                cardClicked(event.cardClicked)
            }
            is SharedFilesEvent.refreshListner -> {
                getAllSharedFiles(event.userId)
            }
            is SharedFilesEvent.deleteFile -> {
                state.value = state.value.copy(cardisClicked = true)
                deleteSharedFile(event.objectId)
            }
        }
    }

    fun cardClicked(statevalue: Boolean) {
        state.value = state.value.copy(cardisClicked = statevalue)
    }

    fun getAllSharedFiles(userId: String) {
        viewModelScope.launch {
            sharedFilesRepositoryImp.getAllFiles(userId)
                .onEach {
                    it.isLoading?.let {
                        state.value = state.value.copy(isloading = it)
                    }
                    it.data?.let {
                        state.value = state.value.copy(data = it)
                        Log.e("userviewmodelstate", state.value.isloading.toString())
                    }
                    it.message?.let {
                        Log.e("getAllSharedFiles", it.description.toString())
                        appendToMessageQueue(it)
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun deleteSharedFile(objectId: String) {
        viewModelScope.launch {
            sharedFilesRepositoryImp.deleteFiles(objectId)
                .onEach {
                    it.data?.let {
                        state.value = state.value.copy(cardisClicked = false)
                        getAllSharedFiles(
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