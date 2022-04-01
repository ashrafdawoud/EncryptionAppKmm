package com.BFCAI.encryptionapp.android.Presentation.Navigation.SendScreen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Interactors.SendRepository.SendRepository
import com.BFCAI.encryptionapp.Interactors.SendRepository.SendRepositoryImp
import com.BFCAI.encryptionapp.Presentation.SendScreen.SendScreenEvent
import com.BFCAI.encryptionapp.Presentation.SendScreen.SendScreenState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.GenericMessageInfoQueueUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendViewModel  @Inject constructor(
    private val sendRepositoryImp: SendRepositoryImp,
    private val context: Context
) : ViewModel(){
    val state :MutableState<SendScreenState> = mutableStateOf(SendScreenState())
    init {
        getAllData(
            context.getSharedPreferences(
                PublicData.GENERAL_PREF,
                AppCompatActivity.MODE_PRIVATE
            ).getString("user_token", "")!!,
            context.getSharedPreferences(
                PublicData.GENERAL_PREF,
                AppCompatActivity.MODE_PRIVATE
            ).getString("User_Id", "")!!,
        )
    }

    fun onTriggerEnvent(event : SendScreenEvent){
        when(event){
            is SendScreenEvent.refreshListner ->{
                getAllData(
                    context.getSharedPreferences(
                        PublicData.GENERAL_PREF,
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("user_token", "")!!,
                    context.getSharedPreferences(
                        PublicData.GENERAL_PREF,
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("User_Id", "")!!,
                )
            }
            is SendScreenEvent.deleteContact ->{
                deleteContact(
                    context.getSharedPreferences(
                        PublicData.GENERAL_PREF,
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("user_token", "")!!,
                    event.objectId
                )
            }
        }
    }

    fun getAllData(token:String , contact1:String){
        viewModelScope.launch {
            sendRepositoryImp.getMyContacts(token , contact1).onEach {
                it.isLoading?.let {
                    state.value = state.value.copy(isloading = it)
                }
                it.data?.let {
                    state.value = state.value.copy(data = it)
                }
                it.message?.let {
                    appendToMessageQueue(it)
                }
            }   .launchIn(viewModelScope)
        }
    }
    fun deleteContact(token:String , contactId:String){
        viewModelScope.launch {
            sendRepositoryImp.deleteOneContact(token = token , contactId = contactId).onEach {
                it.isLoading?.let {
                    state.value = state.value.copy(isloading = it)
                }
                it.data?.let {
                    onTriggerEnvent(SendScreenEvent.refreshListner)
                }
                it.message?.let {
                    appendToMessageQueue(it)
                }
            }   .launchIn(viewModelScope)
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