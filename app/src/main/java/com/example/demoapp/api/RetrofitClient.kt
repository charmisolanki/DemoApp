package com.example.demoapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {

        private const val base_url = "https://fakestoreapi.com/"
        private var INSTANCE: Retrofit? = null

        fun getInstance() : Retrofit {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
