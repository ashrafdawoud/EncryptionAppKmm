package com.BFCAI.encryptionapp.DataSource.Network.Mappers

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SenderOrReciverDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SharedFilesDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SharedFilesItemDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SharedfileDto
import com.BFCAI.encryptionapp.Domain.Model.SenderOrReciverModel
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesItemModel
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesModel
import com.BFCAI.encryptionapp.Domain.Model.SharedfileModel

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
        fileDto = fileDto.toFileModel(),
        sender = sender.toSenderOrREciverModel(),
        reciver = reciver.toSenderOrREciverModel(),
    )
}
fun SenderOrReciverDto.toSenderOrREciverModel():SenderOrReciverModel{
    return SenderOrReciverModel(
        objectId = objectId,
        username = username,
        email = email
    )
}
fun SharedfileDto.toFileModel(): SharedfileModel {
    return SharedfileModel(
        objectId = objectId,
        file_type = file_type,
        encryption_tool_id = encryption_tool_id,
        user_id = user_id,
        file = file
    )
}