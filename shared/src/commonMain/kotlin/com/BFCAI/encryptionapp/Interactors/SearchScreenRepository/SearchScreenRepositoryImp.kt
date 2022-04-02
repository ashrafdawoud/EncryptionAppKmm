package com.BFCAI.encryptionapp.Interactors.SearchScreenRepository

import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SearchScreenCalls.SearchScreenInterface
import com.BFCAI.encryptionapp.Domain.Model.ContactsCopyModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Model.UIComponentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchScreenRepositoryImp(
    private val searchScreenInterface: SearchScreenInterface
) : SearchScreenRepository {
    override suspend fun search(token: String, email: String): Flow<DataState<ContactsCopyModel>> =
        flow {
            emit(DataState.loading())
            try {
                val data = searchScreenInterface.search(token, email)
                emit(DataState.data(message = null, data = data))
            } catch (e: Exception) {
                emit(
                    DataState.error(
                        message = GenericMessageInfo.Builder()
                            .id("searchContacts.ERROR")
                            .title("Error")
                            .description(e.message ?: "UnKnownErroe")
                            .uiComponentType(UIComponentType.SnackBar)
                            .onDismiss { }
                    )
                )
            }
        }

    override suspend fun addContacts(
        token: String,
        contact1: String,
        contact2: String
    ): Flow<DataState<String>> = flow {
        emit(DataState.loading())
        try {
            val data = searchScreenInterface.AddToContacts(token, contact1 ,contact2)
            if (data.equals("Success")){
                emit(DataState.data(message = null, data = data))
            }else{
                emit(
                    DataState.error(
                        message = GenericMessageInfo.Builder()
                            .id("addContacts.ERROR")
                            .title("Error")
                            .description(data ?: "UnKnownErroe")
                            .uiComponentType(UIComponentType.SnackBar)
                            .onDismiss { }
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                DataState.error(
                    message = GenericMessageInfo.Builder()
                        .id("addContacts.ERROR")
                        .title("Error")
                        .description(e.message ?: "UnKnownErroe")
                        .uiComponentType(UIComponentType.SnackBar)
                        .onDismiss { }
                )
            )
        }
    }
}