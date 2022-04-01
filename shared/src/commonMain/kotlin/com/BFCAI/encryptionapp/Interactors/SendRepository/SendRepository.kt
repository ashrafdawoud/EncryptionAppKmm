package com.BFCAI.encryptionapp.Interactors.SendRepository

import com.BFCAI.encryptionapp.Domain.Model.ContactsModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import kotlinx.coroutines.flow.Flow

interface SendRepository {
    suspend fun getMyContacts(token:String , contact1:String): Flow<DataState<ContactsModel>>
    suspend fun deleteOneContact(contactId:String,token: String): Flow<DataState<String>>
}