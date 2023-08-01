package com.example.androidcleancodemvvm2.pressentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcleancodemvvm2.R
import com.example.androidcleancodemvvm2.adapters.ProductRescyclerAdapter
import com.example.androidcleancodemvvm2.databinding.ActivityMainBinding
import com.example.androidcleancodemvvm2.pressentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductRescyclerAdapter
    private lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)


        binding.apply {

            productRecycler.layoutManager = LinearLayoutManager(this@MainActivity)

            viewModel.state.observe(this@MainActivity){
                if (!it.isLoading){
                    progressBar.visibility = View.INVISIBLE
                    if (it.error.isNotBlank()){
                        textError.visibility = View.VISIBLE
                        textError.text = it.error
                    }else{
                        productRecycler.visibility = View.VISIBLE
                        productAdapter = ProductRescyclerAdapter(it.product)
                        productRecycler.adapter = productAdapter
                    }
                }
            }
        }
    }
}