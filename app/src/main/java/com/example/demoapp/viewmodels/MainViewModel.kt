package com.example.demoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.models.ProductList
import com.example.demoapp.models.ProductListItem
import com.example.demoapp.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository) : ViewModel(){

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProductList(10)
        }
    }

    val products : LiveData<List<ProductListItem>>
    get() = repository.productData
}