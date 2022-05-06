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
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesEvent
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.GenericMessageInfoQueueUtil
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFilesViewModel @Inject constructor(
    private val userFileRepository: UserFileRepository,
    private val context: Context
) : ViewModel() {
    val state: MutableState<UserFilesState> = mutableStateOf(UserFilesState())

    init {
        getAllUserFiles(
            context.getSharedPreferences(
                PublicData.GENERAL_PREF,
                AppCompatActivity.MODE_PRIVATE
            ).getString("User_Id", "")!!,
            context.getSharedPreferences(
                PublicData.GENERAL_PREF,
                AppCompatActivity.MODE_PRIVATE
            ).getString("user_token", "")!!
        )
    }

    fun onEventTrigger(event: UserFilesEvent) {
        when (event) {
            is UserFilesEvent.cardIsClicked -> {
                cardClicked(event.cardClicked)
            }
            is UserFilesEvent.refreshListner -> {
                getAllUserFiles(
                    event.userId,
                    context.getSharedPreferences(
                        PublicData.GENERAL_PREF,
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("user_token", "")!!
                )
            }
            is UserFilesEvent.deleteFile -> {
                state.value = state.value.copy(cardisClicked = true)
                deleteUserFile(
                    event.objectId,
                    context.getSharedPreferences(
                        PublicData.GENERAL_PREF,
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("user_token", "")!!
                )
            }
            is UserFilesEvent.shareFile -> {
                shareFile(
                    fileId = event.fileId,
                    senderId = event.senderid,
                    reciverid = event.reciverId,
                    context.getSharedPreferences(
                        PublicData.GENERAL_PREF,
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("user_token", "")!!
                )
            }

            is UserFilesEvent.resetState -> {
                state.value =state.value.copy(isloading = false , uploaded = null , data = null , queue =  Queue(mutableListOf()) , cardisClicked = false )
                getAllUserFiles(
                    context.getSharedPreferences(
                        PublicData.GENERAL_PREF,
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("User_Id", "")!!,
                    context.getSharedPreferences(
                        PublicData.GENERAL_PREF,
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("user_token", "")!!
                )
            }
        }
    }

    fun cardClicked(statevalue: Boolean) {
        state.value = state.value.copy(cardisClicked = statevalue)
    }

    fun getAllUserFiles(userId: String, token: String) {
        viewModelScope.launch {
            userFileRepository.getAllFiles(userId, token)
                .onEach {
                    it.isLoading.let {
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

    fun deleteUserFile(objectId: String, token: String) {
        viewModelScope.launch {
            userFileRepository.deleteFile(objectId, token)
                .onEach {
                    it.data?.let {
                        state.value = state.value.copy(cardisClicked = false)
                        getAllUserFiles(
                            context.getSharedPreferences(
                                PublicData.GENERAL_PREF,
                                AppCompatActivity.MODE_PRIVATE
                            ).getString("User_Id", "")!!,
                            token
                        )
                    }
                    it.message?.let {
                        appendToMessageQueue(it)
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun shareFile(
        fileId: String,
        senderId: String,
        reciverid: String,
        token: String
    ) {
        viewModelScope.launch {
            userFileRepository.shareFile(fileId, senderId, reciverid, token)
                .onEach {
                    it.isLoading.let {
                        state.value = state.value.copy(isloading = it)
                    }
                    it.data?.let {
                        state.value = state.value.copy(uploaded = it)
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