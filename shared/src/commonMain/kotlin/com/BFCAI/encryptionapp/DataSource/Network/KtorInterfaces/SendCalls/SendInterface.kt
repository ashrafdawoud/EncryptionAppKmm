package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SendCalls

import com.BFCAI.encryptionapp.Domain.Model.ContactsModel

interface SendInterface {
    suspend fun getMyContacts(token:String , contact1:String):ContactsModel
    suspend fun deleteOneContact(contactId:String,token: String):String
}