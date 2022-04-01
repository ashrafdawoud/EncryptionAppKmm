package com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Interactors.UserFileRepository.UserFileRepositoryImp
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenEvents
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenState
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.GenericMessageInfoQueueUtil
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.util.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EncryptionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val uploadFileRepositoryImp: UserFileRepositoryImp,
    private val context: Context
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
                state.value = state.value.copy(fileBytes = event.file, filename = event.filename , userid = event.userid)
            }
        }
    }

    @OptIn(InternalAPI::class)
    fun uploadfiles(navController: NavController) {
        viewModelScope.launch {
            uploadFileRepositoryImp.uploadFile(
                state.value.fileBytes,
                state.value.filename,
                state.value.fileType,
                state.value.encryType,
                state.value.userid!!,
                context.getSharedPreferences(
                    PublicData.GENERAL_PREF,
                    AppCompatActivity.MODE_PRIVATE
                ).getString("user_token", "")!!
            ).onEach {
                it.isLoading?.let {
                    Log.e("viewmodelstate",it.toString())
                    state.value = state.value.copy(isloading = it)
                }
                it.data?.let {
                    state.value = state.value.copy(isloading = false)
                    Log.e("viewmodelstate",it.toString())
                    navController.navigate(Screens.SuccessScreen.rout)
                    resetState()
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
    fun resetState(){
        state.value = state.value.copy(
            isloading = false,
            filename= "No File Selected Yet",
            fileType = "application/pdf",
            fileBytes = "".toByteArray(),
            encryType = "AES/CBC/NoPadding",
            userid= null,
            queue = Queue(mutableListOf())
        )
    }
}
