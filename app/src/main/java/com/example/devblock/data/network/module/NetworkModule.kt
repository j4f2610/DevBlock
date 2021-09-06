package com.example.devblock.data.network.module

import com.example.devblock.BuildConfig
import com.example.devblock.data.network.Interceptor.LogInterceptor
import com.example.devblock.data.network.api.APIService
import com.example.devblock.data.network.qualifier.RetrofitQualifier
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    @RetrofitQualifier
    internal fun createRetrofit(
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(initHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    private fun initHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .addInterceptor(LogInterceptor())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideAPIService(@RetrofitQualifier retrofit: Retrofit) =
        retrofit.create(APIService::class.java)

}