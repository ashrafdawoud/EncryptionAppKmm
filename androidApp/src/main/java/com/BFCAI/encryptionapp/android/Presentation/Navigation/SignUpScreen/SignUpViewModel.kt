package com.BFCAI.encryptionapp.android.Presentation.Navigation.SignUpScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.BFCAI.encryptionapp.Interactors.UserReposetories.UserRepository
import com.BFCAI.encryptionapp.Interactors.UserReposetories.UserRepositoryImp
import com.BFCAI.encryptionapp.Presentation.SignUpScreen.SignupScreenEvent
import com.BFCAI.encryptionapp.Presentation.SignUpScreen.SignupScreenState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.GenericMessageInfoQueueUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepositoryImp: UserRepository
) : ViewModel() {
    val state: MutableState<SignupScreenState> = mutableStateOf(SignupScreenState())
    fun onTriggerEvent(event: SignupScreenEvent){
        when (event){
            is SignupScreenEvent.signup ->{
                signup(event.email , event.password , event.username,event.openLoginScreen )
            }
        }
    }
    fun signup(email: String, password: String ,username:String , openLoginScreen:()->Unit) {
        viewModelScope.launch {
            userRepositoryImp.signUp(email, password , username).onEach {
                it.isLoading?.let {
                    state.value = state.value.copy(isloading = it)
                    Log.e("signupviewmodelstate",it.toString())
                }
                it.data?.let {
                    //state.value = state.value.copy(data = it)
                    Log.e("signupviewmodelstate",it.toString())
                    activateEmail(email,openLoginScreen)
                }
                it.message?.let {
                    appendToMessageQueue(it)
                    Log.e("signupviewmodelstateerr",it.description.toString())
                }
            }.launchIn(viewModelScope)
        }
    }
    fun activateEmail(email: String, openLoginScreen:()->Unit) {
        viewModelScope.launch {
            userRepositoryImp.ActivateEmail(email).onEach {
                it.isLoading?.let {
                    state.value = state.value.copy(isloading = it)
                    Log.e("activateEmail",it.toString())
                }
                it.data?.let {
                    state.value = state.value.copy(data = "we send to your email to Activate it")
                    Log.e("activateEmail",it.toString())
                    openLoginScreen()
                }
                it.message?.let {
                    appendToMessageQueue(it)
                    Log.e("activateEmail",it.description.toString())
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