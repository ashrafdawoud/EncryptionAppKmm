package com.example.food1fork.android.DI

import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SearchScreenCalls.SearchScreenInterface
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SendCalls.SendInterface
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SharedFilesCalls.SharedFilesInterface
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserFilesCalls.UserFilesInterface
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserCalls.UserInterface
import com.BFCAI.encryptionapp.Interactors.SearchScreenRepository.SearchScreenRepository
import com.BFCAI.encryptionapp.Interactors.SearchScreenRepository.SearchScreenRepositoryImp
import com.BFCAI.encryptionapp.Interactors.SendRepository.SendRepository
import com.BFCAI.encryptionapp.Interactors.SendRepository.SendRepositoryImp
import com.BFCAI.encryptionapp.Interactors.SharedFilesRepository.SharedFilesRepositoryImp
import com.BFCAI.encryptionapp.Interactors.UserFileRepository.UserFileRepositoryImp
import com.BFCAI.encryptionapp.Interactors.UserReposetories.UserRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        userInterface: UserInterface,
    ):UserRepositoryImp{
        return UserRepositoryImp(userInterface)
    }
    @Singleton
    @Provides
    fun provideUploadFileRepository(
        uploadFileInterface: UserFilesInterface,
    ):UserFileRepositoryImp{
        return UserFileRepositoryImp(uploadFileInterface)
    }
    @Singleton
    @Provides
    fun provideSharedFileRepository(
        sharedFilesInterface: SharedFilesInterface,
    ):SharedFilesRepositoryImp{
        return SharedFilesRepositoryImp(sharedFilesInterface)
    }
    @Singleton
    @Provides
    fun provideSendRepository(
        sendInterface: SendInterface,
    ):SendRepositoryImp{
        return SendRepositoryImp(sendInterface)
    }
    @Singleton
    @Provides
    fun provideSearchScreenRepository(
        searchScreenInterface: SearchScreenInterface,
    ): SearchScreenRepositoryImp {
        return SearchScreenRepositoryImp(searchScreenInterface)
    }
}