package com.example.demoapp

import android.app.Application
import com.example.demoapp.api.ProductListService
import com.example.demoapp.api.RetrofitClient
import com.example.demoapp.db.ProductDatabase
import com.example.demoapp.repository.ProductRepository

class ProductApplication : Application() {

    lateinit var productRepository: ProductRepository

    override fun onCreate() {
        super.onCreate()
        initialize();
    }

    private fun initialize() {
        // creating common repository object here so it can be accessible from anywhere inside app
        val productListService = RetrofitClient.getInstance().create(ProductListService::class.java)
        val database = ProductDatabase.getDatabase(applicationContext)
        productRepository = ProductRepository(productListService, database)
    }


}