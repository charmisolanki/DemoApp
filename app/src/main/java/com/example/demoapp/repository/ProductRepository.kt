package com.example.demoapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demoapp.api.ProductListService
import com.example.demoapp.db.ProductDatabase
import com.example.demoapp.models.ProductListItem

class ProductRepository(private val productListService: ProductListService , private val productDatabase: ProductDatabase) {

    private val productsLiveData = MutableLiveData<List<ProductListItem>>()

    val productData: LiveData<List<ProductListItem>>
    get() = productsLiveData

    // get all product list
    suspend fun getProductList(limit: Int){

        /* if database empty */
        if (productDatabase.productDao().getProducts().isEmpty()) {

            val result = productListService.getProductList(limit)
            if (result?.body() != null) {

                val products: List<ProductListItem> = result.body()!!
                productsLiveData.postValue(products) // updating UI
                productDatabase.productDao().addProducts(products) // storing data in database

            }
        } else {
                val products = productDatabase.productDao().getProducts()
                productsLiveData.postValue(products)

        }
    }

    // update product if user click on wishlist icon
    suspend fun updateProduct(isAdded: Int, prodId: Int){
        if(productDatabase.productDao().getProducts().isNotEmpty()) {
            productDatabase.productDao().updateProduct(isAdded, prodId)
        }
    }

    // get specific product for detail page based on product id
    suspend fun getProduct(id: Int) : ProductListItem{
        return productDatabase.productDao().getProduct(id)

    }

    // get all favourite products
    suspend fun getWishListProduct() : List<ProductListItem>{
        return productDatabase.productDao().getFavoriteProducts()

    }
}