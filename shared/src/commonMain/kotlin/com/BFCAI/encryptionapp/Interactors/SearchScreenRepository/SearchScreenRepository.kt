package com.BFCAI.encryptionapp.Interactors.SearchScreenRepository

import com.BFCAI.encryptionapp.Domain.Model.ContactsCopyModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import kotlinx.coroutines.flow.Flow

interface SearchScreenRepository {
    suspend fun search(token:String , email:String) : Flow<DataState<ContactsCopyModel>>
    suspend fun addContacts(token:String , contact1:String , contact2:String) : Flow<DataState<String>>
}