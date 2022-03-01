package com.BFCAI.encryptionapp.android.Di

import com.BFCAI.encryptionapp.DataSource.Network.KtorClientFactory
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UploudFilesCalls.UploadFileImp
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UploudFilesCalls.UploadFileInterface
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserCalls.UserInterface
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserCalls.UserInterfaceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return KtorClientFactory().build()
    }
    @Singleton
    @Provides
    fun provideUserInterface(
        httpClient: HttpClient,
    ): UserInterface {
        return UserInterfaceImp(
            client = httpClient,
        )
    }
    @Singleton
    @Provides
    fun provideUploadFileInterface(
        httpClient: HttpClient,
    ): UploadFileInterface {
        return UploadFileImp(
            client = httpClient,
        )
    }
}