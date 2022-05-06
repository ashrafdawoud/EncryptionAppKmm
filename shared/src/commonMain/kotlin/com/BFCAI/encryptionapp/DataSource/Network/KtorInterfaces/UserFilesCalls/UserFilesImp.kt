package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserFilesCalls

import android.util.Log
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.*
import com.BFCAI.encryptionapp.DataSource.Network.Mappers.toUserFilesModel
import com.BFCAI.encryptionapp.DataSource.Network.Mappers.toUserModel
import com.BFCAI.encryptionapp.Domain.Model.UserFilesModel
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.util.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.Serializable

class UserFilesImp constructor(
    val client: HttpClient,

    ) : UserFilesInterface {
    @OptIn(InternalAPI::class)
    override suspend fun uploadFile(file: ByteArray,filename :String,filetype :String,encryptionTool :String,userid :String ,token:String): String {

         val response = client.submitFormWithBinaryData<HttpResponse>(
             formData = formData {
                 append(
                     "file",
                     InputProvider { buildPacket { writeFully(file) } })
             }) {
             url("https://encyriptionapp.b4a.io/parse/files/hello.${filename}")
            headers {
                appendAll("X-Parse-Application-Id", listOf(PublicData.Application_Id))
                appendAll("X-Parse-REST-API-Key", listOf(PublicData.REST_API_Key))
                append("X-Parse-Session-Token", token)
            }
             body = file
        }
        if (response.status.value in 200..299) {
            val res = response.receive<UploadedFileResponceDto>()
            Log.e("Image_url",res.url + "  "+file)
            val response2 = client.post<HttpResponse> {
                url("${PublicData.BASEURL}" + "classes/UserFiles")
                headers {
                    append("X-Parse-Application-Id", PublicData.Application_Id)
                    append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                    append("Content-Type", "application/json")
                    append("X-Parse-Session-Token", token)
                }
                body = UploadFileDto(
                    file = FileDto(
                        __type = "File",
                        name = res.name,
                        url = res.url
                    ),
                    file_type = filetype,
                    encryption_tool_id = encryptionTool,
                    user_id = userid
                )
            }
            if (response2.status.value in 200..299) {
                return "Success"
            } else {
                return response2.status.value.toString()
            }
        }else{
            return response.content.toString()
        }

    }

    override suspend fun getAllFiles(userid: String,token:String): UserFilesModel {
        return client.get<UserFilesDto>{
            url("${PublicData.BASEURL}"+"classes/UserFiles?where={\"user_id\":\"$userid\"}")
            headers {
                append("X-Parse-Application-Id",PublicData.Application_Id )
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                append("X-Parse-Revocable-Session", "1")
                append("X-Parse-Session-Token", token)
            }
        }.toUserFilesModel()
    }
    override suspend fun deleteFile(objectId: String,token:String):String {
        val clientreq =  client.delete<HttpResponse>{
            url("${PublicData.BASEURL}"+"classes/UserFiles/$objectId")
            headers {
                append("X-Parse-Application-Id",PublicData.Application_Id )
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                append("X-Parse-Session-Token", token)
            }
        }
        if (clientreq.status.value in 200..299){
            return "sucsess"
        }else{
            return "fail"
        }
    }

    override suspend fun shareFile(
        fileId: String,
        senderId: String,
        reciverid: String,
        token: String
    ): String {
        val clientreq =  client.post<HttpResponse>{
            url(PublicData.BASEURL +"classes/SharedFiles")
            headers {
                append("X-Parse-Application-Id",PublicData.Application_Id )
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                append("X-Parse-Session-Token", token)
                append("Content-Type", "application/json")
            }
            body = ShareFileDto(
                ObjectDto(
                    __type = "Pointer",
                    className = "UserFiles",
                    objectId = fileId
                ),
                ObjectDto(
                    __type = "Pointer",
                    className = "_User",
                    objectId = senderId
                ),
                ObjectDto(
                    __type = "Pointer",
                    className = "_User",
                    objectId = reciverid
                ),
            )
        }
        if (clientreq.status.value in 200..299){
            return "sucsess"
        }else{
            return "fail"
        }
    }


}

@Serializable
data class UploadedFileResponceDto(
    val url: String,
    val name: String
)