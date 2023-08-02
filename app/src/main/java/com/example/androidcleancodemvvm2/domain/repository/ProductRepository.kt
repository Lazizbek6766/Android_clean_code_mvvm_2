package com.example.androidcleancodemvvm2.domain.repository

import com.example.androidcleancodemvvm2.domain.common.Resource
import com.example.androidcleancodemvvm2.domain.model.ProductItem

interface ProductRepository {
    suspend fun  getProducts(): List<ProductItem>
    suspend fun addProduct(product: ProductItem): Resource<Unit>
}