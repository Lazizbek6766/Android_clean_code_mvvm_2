package com.example.androidcleancodemvvm2.data.repository

import com.example.androidcleancodemvvm2.data.api.MyApi
import com.example.androidcleancodemvvm2.data.extensions.toProduct
import com.example.androidcleancodemvvm2.data.model.ProductModelItem
import com.example.androidcleancodemvvm2.data.model.Rating
import com.example.androidcleancodemvvm2.domain.common.Resource
import com.example.androidcleancodemvvm2.domain.model.ProductItem
import com.example.androidcleancodemvvm2.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: MyApi
):ProductRepository {
    override suspend fun getProducts(): List<ProductItem> {
        return api.getProducts().map { it.toProduct() }
    }

    override suspend fun addProduct(product: ProductItem): Resource<Unit> {
        return try {
            val productModelItem = ProductModelItem(
                category = product.category,
                description = product.description,
                id = product.id,
                image = product.image,
                price = product.price,
                rating = Rating(rate = product.rating.rate, count = product.rating.count),
                title = product.title
            )
            api.addProduct(productModelItem)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Failed to add product: ${e.message}")
        }
    }

}