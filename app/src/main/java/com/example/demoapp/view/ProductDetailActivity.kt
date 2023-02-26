package com.example.demoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.demoapp.ProductApplication
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentProductDeatilBinding
import com.example.demoapp.models.ProductListItem
import kotlinx.coroutines.*

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: FragmentProductDeatilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProductDeatilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent?.getIntExtra("productId", 0)

        //below function is getting product detail from database based on productId
        CoroutineScope(Dispatchers.Main).launch {
            getDetail(id)
        }
    }

    // Used withContext() on Dispatchers.Main as UI is updating based on single task execution to fetch details from DB
    private suspend fun getDetail(id: Int?) {
        if(id != 0) {
            val productDetails = withContext(Dispatchers.Main) {

                val repository = (application as ProductApplication).productRepository
                repository.getProduct(id!!)
            }
            setData(productDetails)
        }
    }

    private fun setData(product: ProductListItem?) {
        binding.tvTitle.text = product?.title
        binding.tvDescription.text = product?.description
        binding.tvPrice.text = "Price - ${getString(R.string.rupee_symbol)} ${product?.price}"

        if (product?.image !== null) {
            Glide.with(this)
                .load(product.image)
                .into(binding.ivProduct)
        } else {
            binding.ivProduct.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }
}