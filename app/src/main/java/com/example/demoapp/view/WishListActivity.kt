package com.example.demoapp.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.ProductApplication
import com.example.demoapp.ProductClickListener
import com.example.demoapp.R
import kotlinx.android.synthetic.main.fragment_wish_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishListActivity : AppCompatActivity() , ProductClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_wish_list)
        getWishListProducts()
    }

    //below function is used to get wishlist products from DB
    private fun getWishListProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            val repository = (application as ProductApplication).productRepository
            val wishListProducts = repository.getWishListProduct()

            if(wishListProducts.isNotEmpty())
            wishListView.adapter = WishListAdapter(wishListProducts,this@WishListActivity)
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