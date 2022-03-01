package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserCalls

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.EmailDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ResponseDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SignUpDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UserDto
import com.BFCAI.encryptionapp.DataSource.Network.Mappers.toUserModel
import com.BFCAI.encryptionapp.Domain.Model.UserModel
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.cio.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

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

    override suspend fun Signup(email: String, password: String, username: String): String {
       val response = client.post<HttpResponse>{
            url("${PublicData.BASEURL}"+"users")
            headers {
                append("X-Parse-Application-Id",PublicData.Application_Id )
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                append("X-Parse-Revocable-Session", "1")
            }
           contentType(ContentType.Application.Json)

            body = SignUpDto(email = email, password = password, username = username)
        }
        if ( response.status.value in 200..299){
            return "Success"
        }else{
            return response.status.value.toString()
        }
    }

    override suspend fun ActivateEmail(email: String): String {
        val response = client.post<HttpResponse>{
            url("https://encyriptionapp.b4a.io/verificationEmailRequest")
            headers {
                append("X-Parse-Application-Id",PublicData.Application_Id )
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
            }
            contentType(ContentType.Application.Json)

            body = EmailDto(email)
        }
        if ( response.status.value in 200..299){
            return "Success"
        }else{
            return response.status.value.toString()
        }
    }

}
