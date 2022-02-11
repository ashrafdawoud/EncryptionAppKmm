package com.BFCAI.encryptionapp.DataSource.Network

import io.ktor.client.*

expect class KtorClientFactory (){
    fun build():HttpClient
}
