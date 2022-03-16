package com.example.food1fork.android.DI

import android.app.Application
import android.content.Context
import com.BFCAI.encryptionapp.android.BaseAplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseAplication {
        return app as BaseAplication
    }
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application
}