package com.BFCAI.encryptionapp.Domain.Model

data class ContactsModel(
    val results : List<ContactsItemModel>
)
data class ContactsCopyModel(
    val results : List<SingleContactModel>
)
data class ContactsItemModel(
    val objectId:String,
    val createdAt:String,
    val updatedAt:String,
    val contact1:SingleContactModel,
    val contact2:SingleContactModel,
)
data class SingleContactModel(
    val objectId:String,
    val username:String,
    val emailVerified:Boolean,
    val emailCopy:String,
)
