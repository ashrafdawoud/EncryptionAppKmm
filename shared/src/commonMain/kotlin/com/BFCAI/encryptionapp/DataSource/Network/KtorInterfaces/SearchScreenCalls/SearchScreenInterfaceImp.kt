package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SearchScreenCalls

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.*
import com.BFCAI.encryptionapp.DataSource.Network.Mappers.ToContactsModel
import com.BFCAI.encryptionapp.DataSource.Network.Mappers.toContactCopyModel
import com.BFCAI.encryptionapp.Domain.Model.ContactsCopyModel
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class SearchScreenInterfaceImp(
    val httpClient: HttpClient
) :SearchScreenInterface{
    override suspend fun search(token:String , email:String): ContactsCopyModel {
        return httpClient.request<ContactsCopyDto> {
            url("${PublicData.BASEURL}" + "users?where={\"emailCopy\":{\"\$regex\":\"^"+email+"\"}}")
            headers {
                append("X-Parse-Application-Id", PublicData.Application_Id)
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                append("X-Parse-Session-Token", token)
            }
        }.toContactCopyModel()
    }

    override suspend fun AddToContacts(token: String , contact1:String , contact2:String): String {
        val requist =  httpClient.post<HttpResponse> {
            url("${PublicData.BASEURL}" + "classes/Contacts_list")
            headers {
                append("X-Parse-Application-Id", PublicData.Application_Id)
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                append("Content-Type", "application/json")
                append("X-Parse-Session-Token", token)
            }
            body = AddContactDto(
                Contact(
                    "Pointer",
                    "_User",
                    contact1
                ),
                Contact(
                    "Pointer",
                    "_User",
                    contact2
                )
            )
        }
        if ( requist.status.value in 200..299){
            return "Success"
        }else{
            return requist.status.value.toString()
        }
    }
}