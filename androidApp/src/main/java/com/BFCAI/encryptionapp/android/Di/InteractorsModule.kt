package com.example.food1fork.android.DI

import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UploudFilesCalls.UploadFileInterface
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserCalls.UserInterface
import com.BFCAI.encryptionapp.Interactors.UploadFileRepository.UploadFileRepositoryImp
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
        uploadFileInterface: UploadFileInterface,
    ):UploadFileRepositoryImp{
        return UploadFileRepositoryImp(uploadFileInterface)
    }
}