package com.example.androidcleancodemvvm2.domain.use_case.new_product

import com.example.androidcleancodemvvm2.data.model.ProductReq
import com.example.androidcleancodemvvm2.data.model.ProductRes
import com.example.androidcleancodemvvm2.domain.common.Resource
import com.example.androidcleancodemvvm2.domain.repository.ProductRepository
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: ProductReq): Resource<ProductRes>? {
        return try {
            val createdProduct = productRepository.createProduct(product)
            Resource.Success(createdProduct)
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }
    }
}
