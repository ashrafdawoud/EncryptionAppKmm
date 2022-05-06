package com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Interactors.UserReposetories.UserRepository
import com.BFCAI.encryptionapp.Interactors.UserReposetories.UserRepositoryImp
import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent
import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.GenericMessageInfoQueueUtil
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepositoryImp: UserRepository,
    private val context: Context
) : ViewModel() {
    val state: MutableState<LoginScreenState> = mutableStateOf(LoginScreenState())
    fun onTriggerEvent(event: LoginScreenEvent){
        when (event){
            is LoginScreenEvent.login ->{
                login(event.email , event.password , event.openHomeScreen )
            }
        }
    }
    fun login(email: String, password: String ,openHomeScreen:()-> Unit) {
        viewModelScope.launch {
            userRepositoryImp.login(email, password).onEach {
                it.isLoading?.let {
                    Log.e("viewmodelstate",it.toString())
                    state.value = state.value.copy(isloading = it)
                }
                it.data?.let {
                    context.getSharedPreferences(PublicData.GENERAL_PREF, AppCompatActivity.MODE_PRIVATE)
                        .edit()
                        .putString("User_Id",it.objectId)
                        .putString("User_Name",it.username)
                        .putString("User_Email",it.email)
                        .putString("user_token",it.sessionToken)
                        .commit()
                    Log.e("viewmodelstate",it.toString())
                    state.value = state.value.copy(data = it)
                    openHomeScreen()
                    resetState()
                }
                it.message?.let {
                    Log.e("viewmodelstate",it.toString())
                    appendToMessageQueue(it)
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
            data = null,
            queue = Queue(mutableListOf())
        )
    }
}