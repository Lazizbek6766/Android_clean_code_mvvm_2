package com.example.androidcleancodemvvm2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcleancodemvvm2.databinding.ProductRowBinding
import com.example.androidcleancodemvvm2.domain.model.ProductItem

class ProductRescyclerAdapter(
    val productList:List<ProductItem>
):RecyclerView.Adapter<ProductRescyclerAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ProductRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.productName.text =  productList[position].title
    }
}