package com.BFCAI.encryptionapp.android.Di

import com.BFCAI.encryptionapp.DataSource.Network.KtorClientFactory
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SharedFilesCalls.SharedFilesINterfaceImp
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SharedFilesCalls.SharedFilesInterface
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserFilesCalls.UserFilesImp
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserFilesCalls.UserFilesInterface
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
    ): UserFilesInterface {
        return UserFilesImp(
            client = httpClient,
        )
    }
    @Singleton
    @Provides
    fun provideSharedfileInterface(
        httpClient: HttpClient,
    ): SharedFilesInterface {
        return SharedFilesINterfaceImp(
            client = httpClient,
        )
    }
}