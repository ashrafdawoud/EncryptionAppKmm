package com.BFCAI.encryptionapp.DataSource.Network.Mappers

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.FilesDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UserFilesDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UserFilesItemDto
import com.BFCAI.encryptionapp.Domain.Model.FilesModel
import com.BFCAI.encryptionapp.Domain.Model.UserFilesItemModel
import com.BFCAI.encryptionapp.Domain.Model.UserFilesModel

fun UserFilesDto.toUserFilesModel () : UserFilesModel{
    return UserFilesModel(
        results = results.toUserFilesItemModelList()
    )
}
fun List<UserFilesItemDto>.toUserFilesItemModelList():List<UserFilesItemModel>{
    return map { it.toUserFilesItemModel() }
}
fun UserFilesItemDto.toUserFilesItemModel():UserFilesItemModel{
    return UserFilesItemModel(
        objectId = objectId,
        user_id = user_id,
        file_type = file_type,
        encryption_tool_id = encryption_tool_id,
        updatedAt = updatedAt,
        file = file.toFilesModel()
    )
}
fun FilesDto.toFilesModel():FilesModel{
    return FilesModel(
        __type = __type,
        name = name,
        url = url
    )
}