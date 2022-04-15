package com.BFCAI.encryptionapp.DataSource.Network.Mappers

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ContactsCopyDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ContactsDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ContactsItemDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SingleContactDto
import com.BFCAI.encryptionapp.Domain.Model.ContactsCopyModel
import com.BFCAI.encryptionapp.Domain.Model.ContactsItemModel
import com.BFCAI.encryptionapp.Domain.Model.ContactsModel
import com.BFCAI.encryptionapp.Domain.Model.SingleContactModel

fun ContactsCopyDto.toContactCopyModel(): ContactsCopyModel {
    return ContactsCopyModel(
        results = results.toSingleContactModelList()
    )
}
fun List<SingleContactDto>.toSingleContactModelList():List<SingleContactModel>{
    return map { it.toSingleContactModel() }
}
fun SingleContactDto.toSingleContactModel():SingleContactModel{
    return SingleContactModel(
        objectId = objectId,
        username = username,
        emailVerified=emailVerified,
        emailCopy = emailCopy
    )
}