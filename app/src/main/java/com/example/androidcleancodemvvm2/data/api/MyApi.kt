package com.example.androidcleancodemvvm2.data.api

import com.example.androidcleancodemvvm2.data.model.ProductModelItem
import retrofit2.http.GET

interface MyApi {
    @GET("/products")
    suspend fun getProducts(): List<ProductModelItem>
}