package com.BFCAI.encryptionapp.DataSource.Network.Mappers

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ContactsDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ContactsItemDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SingleContactDto
import com.BFCAI.encryptionapp.Domain.Model.ContactsItemModel
import com.BFCAI.encryptionapp.Domain.Model.ContactsModel
import com.BFCAI.encryptionapp.Domain.Model.SingleContactModel

fun ContactsDto.ToContactsModel():ContactsModel{
    return ContactsModel(
        results = results.ToContactsItemModelList()
    )
}
fun List<ContactsItemDto>.ToContactsItemModelList():List<ContactsItemModel>{
    return map { it.ToContactsItemModel() }
}
fun ContactsItemDto.ToContactsItemModel():ContactsItemModel{
    return ContactsItemModel(
        objectId = objectId,
        createdAt = createdAt,
        updatedAt = updatedAt,
        contact1 = contact1.ToSingleContactModel(),
        contact2 = contact2.ToSingleContactModel()
    )
}
fun SingleContactDto.ToSingleContactModel():SingleContactModel{
    return SingleContactModel(
        objectId = objectId,
        username = username,
        emailVerified=emailVerified
    )
}