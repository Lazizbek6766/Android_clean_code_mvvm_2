package com.example.androidcleancodemvvm2.data.api

import com.example.androidcleancodemvvm2.data.model.ProductModelItem
import com.example.androidcleancodemvvm2.data.model.ProductReq
import com.example.androidcleancodemvvm2.data.model.ProductRes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {
    @GET("products")
    suspend fun getProducts(): List<ProductModelItem>

    @POST("products")
    suspend fun creatProduct(@Body product: ProductReq): Response<ProductRes>

    //iqiucygke
}