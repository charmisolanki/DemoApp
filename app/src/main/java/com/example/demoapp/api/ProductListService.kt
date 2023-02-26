package com.example.demoapp.api

import com.example.demoapp.models.ProductListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductListService {

    @GET("/products")
    suspend fun getProductList(@Query("limit") limit: Int) : Response<List<ProductListItem>>
}