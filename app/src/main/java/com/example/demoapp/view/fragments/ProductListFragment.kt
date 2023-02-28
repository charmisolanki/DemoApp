package com.example.demoapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.demoapp.ProductApplication
import com.example.demoapp.ProductClickListener
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentProductListBinding
import com.example.demoapp.view.adapter.ProductViewAdapter
import com.example.demoapp.viewmodels.MainViewModel
import com.example.demoapp.viewmodels.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        val repository = (activity?.application as ProductApplication).productRepository
         mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        //initializing adapter
        val adapter =  ProductViewAdapter(activity, this)
        binding.productListView.adapter = adapter

        //updating adapter
        mainViewModel.products.observe(viewLifecycleOwner, Observer {
            adapter.updateProducts(it)
        })

    }

    override fun viewDetail(product: Int) {
        val bundle = Bundle()
        bundle.putInt("productId", product)
        findNavController().navigate(R.id.action_productListFragment_to_productDetailViewFragment,bundle)
    }

    override fun addToWishlist(isAdded: Int, prodId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val repository = (activity?.application as ProductApplication).productRepository
            repository.updateProduct(isAdded, prodId)
        }    }
}