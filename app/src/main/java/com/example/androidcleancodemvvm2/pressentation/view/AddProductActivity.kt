package com.example.androidcleancodemvvm2.pressentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidcleancodemvvm2.R
import com.example.androidcleancodemvvm2.data.model.Rating
import com.example.androidcleancodemvvm2.databinding.ActivityAddProductBinding
import com.example.androidcleancodemvvm2.domain.common.Resource
import com.example.androidcleancodemvvm2.domain.model.ProductItem
import com.example.androidcleancodemvvm2.pressentation.viewmodel.AddProductViewModel
import com.example.androidcleancodemvvm2.pressentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddProductBinding
    private lateinit var viewModel: AddProductViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)

        binding.apply {
            addButton.setOnClickListener {
                val product = ProductItem(
                    id = 31, // Assign a unique ID if needed
                    category = "Electronics",
                    description = "A high-quality electronic device",
                    image = "image_url",
                    price = 99.99,
                    rating = Rating(45, 10.0),
                    title = "Sample Product"
                )
                viewModel.addProduct(product)
            }

            viewModel.addProductStatus.observe(this@AddProductActivity, Observer { resource ->
                when (resource) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        showToast("Product added successfully")
                    }
                    is Resource.Error -> {
                        showToast("Failed to add product: ${resource.message}")
                    }
                }
            })
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}