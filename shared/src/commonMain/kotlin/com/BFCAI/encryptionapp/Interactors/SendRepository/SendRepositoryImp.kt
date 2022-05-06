package com.BFCAI.encryptionapp.Interactors.SendRepository

import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SendCalls.SendInterface
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SendCalls.SendInterfaceImp
import com.BFCAI.encryptionapp.Domain.Model.ContactsModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Model.UIComponentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SendRepositoryImp constructor(
    private  val sendInterface: SendInterface
) :SendRepository{
    override suspend fun getMyContacts(token: String , contact1:String): Flow<DataState<ContactsModel>> = flow{
        emit(DataState.loading())
        try {
            val contacts = sendInterface.getMyContacts(token , contact1)
            emit(DataState.data(message = null , data = contacts))
        }catch (e:Exception){
            emit(
                DataState.error(
                    message = GenericMessageInfo.Builder()
                        .id("getMyContacts.ERROR")
                        .title("Error")
                        .description(e.message ?: "UnKnownErroe")
                        .uiComponentType(UIComponentType.SnackBar)
                        .onDismiss { }
                )
            )
        }
    }

    override suspend fun deleteOneContact(
        contactId: String,
        token: String
    ): Flow<DataState<String>> = flow {
        emit(DataState.loading())
        try {
            val contacts = sendInterface.deleteOneContact(token = token , contactId = contactId)
            emit(DataState.data(message = null , data = contacts))
        }catch (e:Exception){
            emit(
                DataState.error(
                    message = GenericMessageInfo.Builder()
                        .id("deleteOneContact.ERROR")
                        .title("Error")
                        .description(e.message ?: "UnKnownErroe")
                        .uiComponentType(UIComponentType.SnackBar)
                        .onDismiss { }
                )
            )
        }
    }
}