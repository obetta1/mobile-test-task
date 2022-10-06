package com.decagon.mobiletesttask.di

import android.util.Log
import com.decagon.mobiletesttask.common.Constants
import com.decagon.mobiletesttask.data.remotedata.RandomUserApi
import com.decagon.mobiletesttask.data.repository.UserRepositoryImpl
import com.decagon.mobiletesttask.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRandomUserApi(): RandomUserApi {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        Log.d("USER", "API MODULE ")

        val okhttpClient = OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()
        Log.d("USER", "Retrofit ")

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URI)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: RandomUserApi): UserRepository {
        Log.d("USER", "REOSITORY MODULE ")

        return UserRepositoryImpl(api)
    }
}