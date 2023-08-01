package com.example.androidcleancodemvvm2.pressentation.model

import com.example.androidcleancodemvvm2.domain.model.ProductItem

class ProductListState(
    val isLoading: Boolean = false,
    val product: List<ProductItem> = emptyList(),
    val error: String = ""
)