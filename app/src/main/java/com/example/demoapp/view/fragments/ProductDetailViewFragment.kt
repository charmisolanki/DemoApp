package com.example.demoapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.demoapp.ProductApplication
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentProductDeatilBinding
import com.example.demoapp.models.ProductListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailViewFragment: Fragment(R.layout.fragment_product_deatil) {

    private lateinit var binding: FragmentProductDeatilBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductDeatilBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt("productId")

        //below function is getting product detail from database based on productId
        CoroutineScope(Dispatchers.Main).launch {
            getDetail(productId)
        }
    }

    // Used withContext() on Dispatchers.Main as UI is updating based on single task execution to fetch details from DB
    private suspend fun getDetail(id: Int?) {
        if(id != 0) {
            val productDetails = withContext(Dispatchers.Main) {

                val repository = (activity?.application as ProductApplication).productRepository
                repository.getProduct(id!!)
            }
            setData(productDetails)
        }
    }

    private fun setData(product: ProductListItem?) {
        binding.tvTitle.text = product?.title
        binding.tvDescription.text = product?.description
        binding.ratingBar.rating = product?.rating!!.rate.toFloat()
        binding.tvPrice.text = "Price - ${getString(R.string.rupee_symbol)} ${product?.price}"

        Glide.with(this)
                .load(product.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivProduct)

    }
}