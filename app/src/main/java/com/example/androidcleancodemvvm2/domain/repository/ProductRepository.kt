package com.example.androidcleancodemvvm2.domain.repository

import com.example.androidcleancodemvvm2.data.model.ProductReq
import com.example.androidcleancodemvvm2.data.model.ProductRes
import com.example.androidcleancodemvvm2.domain.model.ProductItem

interface ProductRepository {
    suspend fun  getProducts(): List<ProductItem>

    suspend fun createProduct(product: ProductReq): ProductRes
}