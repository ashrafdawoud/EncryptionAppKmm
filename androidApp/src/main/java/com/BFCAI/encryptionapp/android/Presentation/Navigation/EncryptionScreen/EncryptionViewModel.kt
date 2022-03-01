package com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen

import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.BFCAI.encryptionapp.Interactors.UploadFileRepository.UploadFileRepositoryImp
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenEvents
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenState
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
            is EncryptionScreenEvents.choose_file -> {
                state.value = state.value.copy(filename = event.filename)
                uploadfiles(event.byteArray)
            }
            is EncryptionScreenEvents.upload_file -> {

            }
        }
    }

    @OptIn(InternalAPI::class)
    fun uploadfiles(file:ByteArray) {
        viewModelScope.launch {
            uploadFileRepositoryImp.uploadFile(
                file,
                state.value.filename
            ).onEach {
                it.isLoading?.let {
                    Log.e("viewmodelstate",it.toString())
                    state.value = state.value.copy(isloading = it)
                }
                it.data?.let {
                    Log.e("viewmodelstate",it.toString())

                }
                it.message?.let {
                    Log.e("viewmodelstatee",it.description.toString())
                    //appendToMessageQueue(it)
                }
            }.launchIn(viewModelScope)
        }
    }
}
