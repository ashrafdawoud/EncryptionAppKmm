package com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Interactors.SearchScreenRepository.SearchScreenRepositoryImp
import com.BFCAI.encryptionapp.Presentation.SearchScreen.SearchScreenEvent
import com.BFCAI.encryptionapp.Presentation.SearchScreen.SearchScreenState
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.GenericMessageInfoQueueUtil
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchScreenRepositoryImp: SearchScreenRepositoryImp,
    private val context: Context
) : ViewModel() {

    val state: MutableState<SearchScreenState> = mutableStateOf(SearchScreenState())

    fun onTriggerEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.Search -> {
                search(
                    event.token,
                    event.email
                )
            }
            is SearchScreenEvent.AddContact -> {
                addContact(event.token , event.contact2)
            }
        }
    }

    fun search(token: String, email: String) {
        viewModelScope.launch {
            searchScreenRepositoryImp.search(token, email)
                .onEach {
                    it.isLoading?.let {
                        state.value = state.value.copy(isloading = it)
                    }
                    it.data?.let {
                        state.value = state.value.copy(data = it)
                    }
                    it.message?.let {
                        appendToMessageQueue(it)
                    }
                }.launchIn(viewModelScope)
        }
    }
    fun addContact(token: String, contact2: String ) {
        viewModelScope.launch {
            searchScreenRepositoryImp.addContacts(
                token,
                context.getSharedPreferences(
                    PublicData.GENERAL_PREF,
                    AppCompatActivity.MODE_PRIVATE
                ).getString("User_Id", "")!!
                ,contact2
            )
                .onEach {
                    it.isLoading?.let {
                        state.value = state.value.copy(isloading = it)
                    }
                    it.data?.let {
                        state.value = state.value.copy(contactAdded = true)
                        delay(500)
                        resetState()
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
    fun resetState(){
        state.value = state.value.copy(
            isloading = false,
            data = null,
            contactAdded = false,
            queue = Queue(mutableListOf())
        )
    }
}