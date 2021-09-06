package com.example.devblock.data.network.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    internal fun provideGSon(): Gson {
        return GsonBuilder()
            .setPrettyPrinting()
            .create()
    }

}