package com.example.demoapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.ProductApplication
import com.example.demoapp.ProductClickListener
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentProductListBinding
import com.example.demoapp.view.ProductViewAdapter
import com.example.demoapp.view.ProductsActivity
import com.example.demoapp.viewmodels.MainViewModel
import com.example.demoapp.viewmodels.MainViewModelFactory

class ProductListFragment: Fragment(), ProductClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentProductListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //creating instance of ViewModel
//        mainViewModel = (activity as ProductsActivity).mainViewModel

        Toast.makeText(activity, "hey from frg 1 ",1).show()
        val repository = (activity?.application as ProductApplication).productRepository
        // mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        //initializing adapter
        val adapter =  ProductViewAdapter(this)
        binding.productListView.adapter = adapter

        //updating adapter
//        mainViewModel.products.observe(viewLifecycleOwner, Observer {
//            adapter.updateProducts(it)
//        })

    }

    override fun viewDetail(product: Int) {
        TODO("Not yet implemented")
    }

    override fun addToWishlist(isAdded: Int, prodId: Int) {
        TODO("Not yet implemented")
    }
}