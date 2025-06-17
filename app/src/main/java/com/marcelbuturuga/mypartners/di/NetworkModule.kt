package com.marcelbuturuga.mypartners.di

import com.marcelbuturuga.mypartners.data.api.PartnerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://662f244a43b6a7dce30e7f2a.mockapi.io/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePartnerApi(retrofit: Retrofit): PartnerApi {
        return retrofit.create(PartnerApi::class.java)
    }
}