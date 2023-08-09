package com.example.androidcleancodemvvm2.data.extensions

import com.example.androidcleancodemvvm2.data.model.ProductModelItem
import com.example.androidcleancodemvvm2.data.model.ProductReq
import com.example.androidcleancodemvvm2.domain.model.ProductItem

fun ProductModelItem.toProduct():ProductItem{
    return ProductItem(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        rating = rating,
        title = title

    )
}
