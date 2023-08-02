package com.example.androidcleancodemvvm2.domain.use_case.new_product

import com.example.androidcleancodemvvm2.domain.common.Resource
import com.example.androidcleancodemvvm2.domain.model.ProductItem
import com.example.androidcleancodemvvm2.domain.repository.ProductRepository
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: ProductItem): Resource<Unit> {
        return try {
            val result = productRepository.addProduct(product)
            result
        } catch (e: Exception) {
            Resource.Error("Failed to add product: ${e.message}")
        }
    }
}
