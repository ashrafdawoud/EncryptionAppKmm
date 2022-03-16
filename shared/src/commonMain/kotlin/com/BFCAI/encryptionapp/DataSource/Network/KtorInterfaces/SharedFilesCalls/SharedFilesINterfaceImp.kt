package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SharedFilesCalls

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SharedFilesDto
import com.BFCAI.encryptionapp.DataSource.Network.Mappers.toSharedFilesModel
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesModel
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class SharedFilesINterfaceImp constructor(
    val client: HttpClient
) : SharedFilesInterface {
    override suspend fun getAllFiles(senderId: String): SharedFilesModel {
        return client.request<SharedFilesDto> {
            url("${PublicData.BASEURL}" + "classes/SharedFiles?include=sender&include=reciver&where={ \"reciver\":{ \"__type\": \"Pointer\", \"className\":\"_User\", \"objectId\": \"$senderId\" }}")
            headers {
                append("X-Parse-Application-Id", PublicData.Application_Id)
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
            }
        }.toSharedFilesModel()
    }

    override suspend fun deleteFiles(ObjectId: String): String {
        val clientreq = client.delete<HttpResponse> {
            url("${PublicData.BASEURL}" + "classes/SharedFiles/$ObjectId")
            headers {
                append("X-Parse-Application-Id", PublicData.Application_Id)
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
            }
        }
        if (clientreq.status.value in 200..299) {
            return "sucsess"
        } else {
            return "fail"
        }
    }
}
