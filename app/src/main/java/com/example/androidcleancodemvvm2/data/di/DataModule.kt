package com.example.androidcleancodemvvm2.data.di

import com.example.androidcleancodemvvm2.data.api.MyApi
import com.example.androidcleancodemvvm2.data.repository.ProductRepositoryImpl
import com.example.androidcleancodemvvm2.domain.common.Constants.BASE_URL
import com.example.androidcleancodemvvm2.domain.repository.ProductRepository
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

    @Provides
    @Singleton
    fun  providePaprikaApi(): MyApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: MyApi):ProductRepository{
        return ProductRepositoryImpl(api)
    }
}