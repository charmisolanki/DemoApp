package com.example.demoapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.ProductApplication
import com.example.demoapp.ProductClickListener
import com.example.demoapp.R
import com.example.demoapp.viewmodels.MainViewModel
import com.example.demoapp.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() , ProductClickListener{

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creating instance of ViewModel
        val repository = (application as ProductApplication).productRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        //initializing adapter
        val adapter =  ProductViewAdapter(this)
        productListView.adapter = adapter

        //updating adapter
        mainViewModel.products.observe(this, Observer {
            adapter.updateProducts(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.wishlist) loadWishListPage()
        return super.onOptionsItemSelected(item)
    }

    override fun viewDetail(id: Int) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("productId", id)
        startActivity(intent)

    }

    override fun addToWishlist(isAdded: Int, prodId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val repository = (application as ProductApplication).productRepository
            repository.updateProduct(isAdded, prodId)
        }
    }

    private fun loadWishListPage(){
        startActivity(Intent(this@MainActivity, WishListActivity::class.java))
    }

}