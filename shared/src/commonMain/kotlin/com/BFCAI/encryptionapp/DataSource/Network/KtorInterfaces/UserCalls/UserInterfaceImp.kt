package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserCalls

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UserDto
import com.BFCAI.encryptionapp.DataSource.Network.Mappers.toUserModel
import com.BFCAI.encryptionapp.Domain.Model.UserModel
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import io.ktor.client.*
import io.ktor.client.request.*

class UserInterfaceImp(
    val client: HttpClient,
    )
    : UserInterface {
    override suspend fun login(email: String, password: String): UserModel {
        return client.get<UserDto>{
            url("${PublicData.BASEURL}"+"login?email=$email&password=$password")
            headers {
                append("X-Parse-Application-Id",PublicData.Application_Id )
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                append("X-Parse-Revocable-Session", "1")
            }
        }.toUserModel()
    }

}