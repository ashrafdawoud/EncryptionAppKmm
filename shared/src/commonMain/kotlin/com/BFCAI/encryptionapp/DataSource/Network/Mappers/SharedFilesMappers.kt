package com.BFCAI.encryptionapp.DataSource.Network.Mappers

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SenderOrReciverDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SharedFilesDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SharedFilesItemDto
import com.BFCAI.encryptionapp.Domain.Model.SenderOrReciverModel
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesItemModel
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesModel

fun SharedFilesDto.toSharedFilesModel():SharedFilesModel{
    return SharedFilesModel(
        results = results.toSharedFilesItemModelList()
    )
}
fun List<SharedFilesItemDto>.toSharedFilesItemModelList():List<SharedFilesItemModel>{
    return map { it.toShareFilesItemModel() }
}
fun SharedFilesItemDto.toShareFilesItemModel():SharedFilesItemModel{
    return SharedFilesItemModel(
        objectId = objectId,
        encryption_tool_id = encryption_tool_id,
        file_type = file_type,
        file = file,
        sender = sender.toSenderOrREciverModel(),
        reciver = reciver.toSenderOrREciverModel(),
        password = password
    )
}
fun SenderOrReciverDto.toSenderOrREciverModel():SenderOrReciverModel{
    return SenderOrReciverModel(
        objectId = objectId,
        username = username,
        emailVerified = emailVerified
    )
}