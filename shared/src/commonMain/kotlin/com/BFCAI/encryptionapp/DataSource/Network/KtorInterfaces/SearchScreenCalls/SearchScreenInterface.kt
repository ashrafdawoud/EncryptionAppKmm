package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SearchScreenCalls

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ContactsCopyDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SingleContactDto
import com.BFCAI.encryptionapp.Domain.Model.ContactsCopyModel

interface SearchScreenInterface {
    suspend fun search(token:String , email:String) : ContactsCopyModel
    suspend fun AddToContacts(token: String ,contact1:String , contact2:String) : String
}