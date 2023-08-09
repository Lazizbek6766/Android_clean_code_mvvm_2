package com.example.androidcleancodemvvm2.data.repository

import com.example.androidcleancodemvvm2.data.api.MyApi
import com.example.androidcleancodemvvm2.data.extensions.ApiException
import com.example.androidcleancodemvvm2.data.extensions.toProduct
import com.example.androidcleancodemvvm2.data.model.ProductReq
import com.example.androidcleancodemvvm2.data.model.ProductRes
import com.example.androidcleancodemvvm2.domain.model.ProductItem
import com.example.androidcleancodemvvm2.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: MyApi
) : ProductRepository {
    override suspend fun getProducts(): List<ProductItem> {
        return api.getProducts().map { it.toProduct() }
    }

    override suspend fun createProduct(product: ProductReq): ProductRes {
        val response = api.creatProduct(product)
        if (response.isSuccessful) {
            val createdProduct = response.body()
            return ProductRes(
                id = createdProduct!!.id,
                title = createdProduct.title,
                price = createdProduct.price,
                description = createdProduct.description,
                image = createdProduct.image,
                category = createdProduct.category,
            )
        } else {
            throw ApiException("Failed to create product")
        }
    }
}
