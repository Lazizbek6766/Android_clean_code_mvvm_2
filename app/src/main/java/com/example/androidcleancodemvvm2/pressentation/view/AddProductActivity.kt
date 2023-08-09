package com.example.androidcleancodemvvm2.pressentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidcleancodemvvm2.data.model.ProductReq
import com.example.androidcleancodemvvm2.databinding.ActivityAddProductBinding
import com.example.androidcleancodemvvm2.domain.common.Resource
import com.example.androidcleancodemvvm2.pressentation.viewmodel.AddProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddProductBinding

    private lateinit var viewModel: AddProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this@AddProductActivity).get(AddProductViewModel::class.java)

        binding.addButton
            .setOnClickListener {
                val title = "Title"
                val price = 9.9
                val description = "des"
                val image = "https://i.pravatar.cc"
                val category = "electronic"
                val productReq = ProductReq(title, price, description, image, category)
                viewModel.addProduct(productReq)
            }

        viewModel.addProductResult.observe(this, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    val createdProduct = result.data
                    Toast.makeText(
                        this,
                        "Product added successfully: ${createdProduct!!.title}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Resource.Error -> {
                    val exception = result.message
                    Toast.makeText(
                        this,
                        "Error adding product: $exception",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        })
    }

}