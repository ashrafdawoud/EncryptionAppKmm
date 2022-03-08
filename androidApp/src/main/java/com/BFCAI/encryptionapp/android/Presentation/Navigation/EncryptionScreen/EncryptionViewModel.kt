package com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.BFCAI.encryptionapp.Interactors.UploadFileRepository.UploadFileRepositoryImp
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenEvents
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.GenericMessageInfoQueueUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class EncryptionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val uploadFileRepositoryImp: UploadFileRepositoryImp
) : ViewModel() {
    val state: MutableState<EncryptionScreenState> = mutableStateOf(EncryptionScreenState())

    fun onTriggerEvent(event: EncryptionScreenEvents) {
        when (event) {
            is EncryptionScreenEvents.choose_Type -> {
                state.value = state.value.copy(fileType = event.type)
            }
            is EncryptionScreenEvents.choose_Encr -> {
                state.value = state.value.copy(encryType = event.enccy)
            }
            is EncryptionScreenEvents.choose_file -> {
                state.value = state.value.copy(fileBytes = event.byteArray, filename = event.filename)
            }
            is EncryptionScreenEvents.upload_file -> {
                uploadfiles()
            }
        }
    }

    @OptIn(InternalAPI::class)
    fun uploadfiles() {
        viewModelScope.launch {
            uploadFileRepositoryImp.uploadFile(
                state.value.fileBytes,
                state.value.filename
            ).onEach {
                it.isLoading?.let {
                    Log.e("viewmodelstate",it.toString())
                    state.value = state.value.copy(isloading = it)
                }
                it.data?.let {
                    state.value = state.value.copy(isloading = false)
                    Log.e("viewmodelstate",it.toString())
                }
                it.message?.let {
                    appendToMessageQueue(it)
                    Log.e("viewmodelstatee",it.description.toString())
                    //appendToMessageQueue(it)
                }
            }.launchIn(viewModelScope)
        }
    }
    fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder) {
        if (!GenericMessageInfoQueueUtil().doesMessageIsAlreadyExistOnQueue(
                state.value.queue,
                messageInfo.build()
            )){
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }

    }
}
