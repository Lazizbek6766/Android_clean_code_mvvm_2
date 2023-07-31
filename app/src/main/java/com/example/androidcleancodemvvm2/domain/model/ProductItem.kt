package com.example.androidcleancodemvvm2.domain.model

import com.example.androidcleancodemvvm2.data.model.Rating

data class ProductItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)