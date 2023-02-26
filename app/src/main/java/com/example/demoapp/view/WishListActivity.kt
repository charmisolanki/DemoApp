package com.example.demoapp.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.ProductApplication
import com.example.demoapp.ProductClickListener
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentWishListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishListActivity : AppCompatActivity() , ProductClickListener {

    private lateinit var binding: FragmentWishListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentWishListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWishListProducts()
    }

    //below function is used to get wishlist products from DB
    private fun getWishListProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            val repository = (application as ProductApplication).productRepository
            val wishListProducts = repository.getWishListProduct()

            if(wishListProducts.isNotEmpty())
                binding.wishListView.adapter = WishListAdapter(wishListProducts,this@WishListActivity)
            else
                Toast.makeText(this@WishListActivity, "No product added to wishlist !!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun viewDetail(productId: Int) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("productId", productId)
        startActivity(intent)
    }

    override fun addToWishlist(isAdded: Int, prodId: Int) {
    }


}