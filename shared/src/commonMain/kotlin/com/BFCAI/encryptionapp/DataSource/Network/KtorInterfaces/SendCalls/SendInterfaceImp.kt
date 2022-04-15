package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SendCalls

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ContactsDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SharedFilesDto
import com.BFCAI.encryptionapp.DataSource.Network.Mappers.ToContactsModel
import com.BFCAI.encryptionapp.DataSource.Network.Mappers.toSharedFilesModel
import com.BFCAI.encryptionapp.Domain.Model.ContactsModel
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import io.ktor.client.*
import io.ktor.client.request.*

class SendInterfaceImp constructor(
    val client: HttpClient
) :SendInterface{
    override suspend fun getMyContacts(token:String , contact1:String): ContactsModel {
        return client.request<ContactsDto> {
            url("${PublicData.BASEURL}" + "classes/Contacts_list?include=contact1&include=contact2&where={ \"contact1\":{ \"__type\": \"Pointer\", \"className\": \"_User\", \"objectId\": \""+contact1+"\" }}")
            headers {
                append("X-Parse-Application-Id", PublicData.Application_Id)
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                append("X-Parse-Session-Token", token)
            }
        }.ToContactsModel()
    }

    override suspend fun deleteOneContact(contactId: String , token: String): String {
        return client.delete<String> {
            url("${PublicData.BASEURL}" + "classes/Contacts_list/"+contactId)
            headers {
                append("X-Parse-Application-Id", PublicData.Application_Id)
                append("X-Parse-REST-API-Key", PublicData.REST_API_Key)
                append("X-Parse-Session-Token", token)
            }
        }
    }
}