package com.example.androidcleancodemvvm2.pressentation.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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

        binding.selectImageButton.setOnClickListener {
            openGalleryForImage()
        }

        binding.addButton
            .setOnClickListener {
                val title = "Title"
                val price = 9.9
                val description = "des"
                val category = "electronic"
                val productReq = ProductReq(title, price, description, viewModel.selectedImageUri.toString(), category)
                viewModel.addProduct(productReq)
                Log.d("Turayev77", "onCreate: ${viewModel.selectedImageUri.toString()}")
            }

        viewModel.addProductResult.observe(this, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    val createdProduct = result.data
                    Toast.makeText(
                        this,
                        "Product added successfully: ${createdProduct!!.image}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("Turayev77", "onCreate: ${createdProduct!!.image}")
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

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImageUri = result.data?.data
            viewModel.selectedImageUri = selectedImageUri
            binding.productImageView.setImageURI(selectedImageUri)
        }
    }

}