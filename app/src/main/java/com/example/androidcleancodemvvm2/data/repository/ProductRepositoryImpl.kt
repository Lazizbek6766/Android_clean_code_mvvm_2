package com.example.androidcleancodemvvm2.data.repository

import com.example.androidcleancodemvvm2.data.api.MyApi
import com.example.androidcleancodemvvm2.data.extensions.toProduct
import com.example.androidcleancodemvvm2.domain.model.ProductItem
import com.example.androidcleancodemvvm2.domain.repository.ProductRepository
import javax.inject.Inject
//fyfytfytfytf
class ProductRepositoryImpl @Inject constructor(
    private val api: MyApi
):ProductRepository {
    override suspend fun getProducts(): List<ProductItem> {
        return api.getProducts().map { it.toProduct() }
    }
//kounnij;pijuo
}