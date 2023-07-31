package com.example.androidcleancodemvvm2.data.di

import com.example.androidcleancodemvvm2.data.api.MyApi
import com.example.androidcleancodemvvm2.domain.common.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    //hatd
    @Provides
    @Singleton
    fun  providePaprikaApi(): MyApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }


}