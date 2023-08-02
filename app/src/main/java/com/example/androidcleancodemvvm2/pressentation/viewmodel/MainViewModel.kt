package com.example.androidcleancodemvvm2.pressentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcleancodemvvm2.domain.common.Resource
import com.example.androidcleancodemvvm2.domain.model.ProductItem
import com.example.androidcleancodemvvm2.domain.use_case.get_product.GetProductUserCase
import com.example.androidcleancodemvvm2.domain.use_case.new_product.AddProductUseCase
import com.example.androidcleancodemvvm2.pressentation.model.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductUserCase: GetProductUserCase,
):ViewModel(){
    private val _state = MutableLiveData<ProductListState>()
    val state: LiveData<ProductListState> = _state
    init {
        getProduct()
    }

    private fun getProduct() {
        getProductUserCase().onEach {  result->
            when(result){
                is Resource.Success ->{
                    _state.value = ProductListState(product = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ProductListState(error = result.message ?: "error")
                }
                is Resource.Loading ->{
                    _state.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}