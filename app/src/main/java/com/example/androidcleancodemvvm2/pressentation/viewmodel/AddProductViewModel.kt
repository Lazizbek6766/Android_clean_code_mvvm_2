package com.example.androidcleancodemvvm2.pressentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcleancodemvvm2.data.model.ProductReq
import com.example.androidcleancodemvvm2.data.model.ProductRes
import com.example.androidcleancodemvvm2.domain.common.Resource
import com.example.androidcleancodemvvm2.domain.use_case.new_product.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddProductViewModel @Inject constructor(private val addProductUseCase: AddProductUseCase) : ViewModel() {

    var selectedImageUri: Uri? = null

    private val _addProductResult = MutableLiveData<Resource<ProductRes>>()
    val addProductResult: LiveData<Resource<ProductRes>> = _addProductResult

    fun addProduct(product: ProductReq) {
        viewModelScope.launch {
            _addProductResult.value = addProductUseCase(product)!!
        }
    }

}
