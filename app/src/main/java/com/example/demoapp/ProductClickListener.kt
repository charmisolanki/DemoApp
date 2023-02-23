package com.example.demoapp

import com.example.demoapp.models.ProductListItem

interface ProductClickListener {
    fun viewDetail(product: Int)
    fun addToWishlist(isAdded : Int, prodId : Int)
}