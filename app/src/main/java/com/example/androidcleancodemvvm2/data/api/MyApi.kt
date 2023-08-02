package com.example.androidcleancodemvvm2.data.api

import com.example.androidcleancodemvvm2.data.model.ProductModelItem
import com.example.androidcleancodemvvm2.domain.common.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {
    @GET("/products")
    suspend fun getProducts(): List<ProductModelItem>

    @POST("/products")
    fun addProduct(@Body product: ProductModelItem): Resource<Unit>
}