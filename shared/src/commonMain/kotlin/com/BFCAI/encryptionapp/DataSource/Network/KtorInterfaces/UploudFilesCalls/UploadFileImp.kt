package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UploudFilesCalls

import android.util.Log
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.FileDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SignUpDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UploadFileDto
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.util.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.math.log
import kotlin.random.Random

class UploadFileImp constructor(
    val client: HttpClient,

    ) : UploadFileInterface {
    @OptIn(InternalAPI::class)
    override suspend fun uploadFile(file: ByteArray,filename :String): String {

         val response = client.submitFormWithBinaryData<HttpResponse>(
             formData = formData {
                 append(
                     "file",
                     InputProvider { buildPacket { writeFully(file) } }
                     /*Headers.build {
                         append(HttpHeaders.ContentType, ContentType.Image.PNG.toString())
                         append(
                             HttpHeaders.ContentDisposition,
                             ContentDisposition.File.withParameter(
                                 ContentDisposition.Parameters.FileName,
                                 "test"
                             )
                         )
                     }*/)
             }) {
             url("https://encyriptionapp.b4a.io/parse/files/hello.${filename}")
            headers {
                appendAll("X-Parse-Application-Id", listOf(PublicData.Application_Id))
                appendAll("X-Parse-REST-API-Key", listOf(PublicData.REST_API_Key))
            }
             body = file
        }
       /* val response = client.post<HttpResponse>("http://encyriptionapp.b4a.io/parse/files/hello.txt") {
            headers.append("X-Parse-Application-Id", PublicData.Application_Id)
            headers.append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
            headers.append(HttpHeaders.ContentType, ContentType.Image.PNG)
            body = formData {
                    this.appendInput(
                        key = "",
                        headers = Headers.build {
                            append(HttpHeaders.ContentType, ContentType.Image.PNG)

                        },
                    ) { buildPacket { writeFully(file) } }
                }


        }*/
        if (response.status.value in 200..299) {
            val res = response.receive<UploadedFileResponceDto>()
            Log.e("Image_url",res.url + "  "+file)
            val response2 = client.post<HttpResponse> {
                url("${PublicData.BASEURL}" + "classes/UserFiles")
                headers {
                    append("X-Parse-Application-Id", PublicData.Application_Id)
                    append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                    append("Content-Type", "application/json")
                }
                body = UploadFileDto(
                    file = FileDto(
                        __type = "File",
                        name = res.name,
                        url = res.url
                    ),
                    file_type = "adsad",
                    encryption_tool_id = "sada",
                    user_id = "sadas"
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

}

@Serializable
data class UploadedFileResponceDto(
    val url: String,
    val name: String
)