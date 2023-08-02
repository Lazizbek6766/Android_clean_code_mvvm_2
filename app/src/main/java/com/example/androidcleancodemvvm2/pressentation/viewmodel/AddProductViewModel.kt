package com.example.androidcleancodemvvm2.pressentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcleancodemvvm2.domain.common.Resource
import com.example.androidcleancodemvvm2.domain.model.ProductItem
import com.example.androidcleancodemvvm2.domain.use_case.new_product.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
): ViewModel(){

    private val _addProductStatus = MutableLiveData<Resource<Unit>>()
    val addProductStatus: LiveData<Resource<Unit>> get() = _addProductStatus

    fun addProduct(product: ProductItem) {
        viewModelScope.launch {
            _addProductStatus.value = Resource.Loading()
            val resource = addProductUseCase(product)
            _addProductStatus.value = resource
        }
    }
}