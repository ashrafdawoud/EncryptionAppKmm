package com.BFCAI.encryptionapp.DataSource.Network.Mappers

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ResponseDto
import com.BFCAI.encryptionapp.Domain.Model.ResponseModel

fun ResponseDto.toResponseModel():ResponseModel{
    return ResponseModel(
        code= code,
        error = error
    )
}
fun ResponseModel.toResponseDto():ResponseDto{
    return ResponseDto(
        code= code,
        error = error
    )
}