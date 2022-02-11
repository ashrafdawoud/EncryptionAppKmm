package com.BFCAI.encryptionapp.DataSource.Network.Mappers

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UserDto
import com.BFCAI.encryptionapp.Domain.Model.UserModel

//extended Function of userDto
fun UserDto.toUserModel(): UserModel {
    return UserModel(
        objectId = objectId,
        username = username,
        email = email,
        emailVerified = emailVerified,
        sessionToken = sessionToken
    )
}

fun UserModel.toUserDto(): UserDto {
    return UserDto(
        objectId = objectId,
        username = username,
        email = email,
        emailVerified = emailVerified,
        sessionToken = sessionToken
    )
}

fun List<UserDto>.toUserModelList(): List<UserModel> {
    return map { it.toUserModel() }
}

fun List<UserModel>.toUserDtoList(): List<UserDto> {
    return map { it.toUserDto() }
}