package com.BFCAI.encryptionapp.DataSource.Network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

actual class KtorClientFactory{
     actual fun build(): HttpClient {
         return HttpClient(Android) {
             install(JsonFeature){
                 serializer = KotlinxSerializer(
                     kotlinx.serialization.json.Json {
                         ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                     }
                 )
                 HttpResponseValidator {
                     validateResponse { response ->
                         val error = response.receive<Error>()
                         var statuscode=response.status.value
                         if (statuscode != 200) {
                             throw Exception(error.message)
                         }
                     }
                 }
             }
         }
     }
}
