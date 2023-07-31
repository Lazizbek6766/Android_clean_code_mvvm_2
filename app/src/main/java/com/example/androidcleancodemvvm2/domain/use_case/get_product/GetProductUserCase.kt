package com.example.androidcleancodemvvm2.domain.use_case.get_product

import com.example.androidcleancodemvvm2.domain.common.Resource
import com.example.androidcleancodemvvm2.domain.model.ProductItem
import com.example.androidcleancodemvvm2.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetProductUserCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<ProductItem>>> = flow {
        try {
            emit(Resource.Loading())
            val productItem = repository.getProducts()
            emit(Resource.Success(productItem))
        }catch (e: IOException){
            emit(Resource.Error("Salom"))
        }

    }
}