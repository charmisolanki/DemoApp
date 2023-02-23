package com.example.demoapp.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.demoapp.models.ProductList
import com.example.demoapp.models.ProductListItem
import retrofit2.http.GET

@Dao
interface ProductDao {

    @Insert
    suspend fun addProducts(products: List<ProductListItem>)

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<ProductListItem>

    @Query("SELECT * FROM products WHERE isWishListed == 1")    // 1 means product is added to wishlist
    suspend fun getFavoriteProducts() : List<ProductListItem>

    @Query("UPDATE products SET isWishListed =:isAdded WHERE id =:prodId")
    suspend fun updateProduct(isAdded: Int, prodId: Int)

    @Query("SELECT * FROM products WHERE id = :prodId")
    suspend fun getProduct(prodId: Int) : ProductListItem
}