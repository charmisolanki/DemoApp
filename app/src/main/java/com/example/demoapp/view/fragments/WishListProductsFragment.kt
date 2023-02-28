package com.example.demoapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.demoapp.ProductApplication
import com.example.demoapp.ProductClickListener
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentWishListBinding
import com.example.demoapp.view.adapter.WishListAdapter
import com.example.demoapp.viewmodels.MainViewModel
import com.example.demoapp.viewmodels.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishListProductsFragment: Fragment(R.layout.fragment_wish_list) , ProductClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentWishListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = (activity?.application as ProductApplication).productRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        getWishListProducts()
    }

    //below function is used to get wishlist products from DB
    private fun getWishListProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            val repository = (activity?.application as ProductApplication).productRepository
            val wishListProducts = repository.getWishListProduct()

            if(wishListProducts.isNotEmpty())
                binding.wishListView.adapter = WishListAdapter(activity, wishListProducts,this@WishListProductsFragment)
            else
                Toast.makeText(activity, "No product added to wishlist !!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun viewDetail(product: Int) {
        val bundle = Bundle()
        bundle.putInt("productId", product)
        findNavController().navigate(R.id.action_wishListProductsFragment_to_productDetailViewFragment,bundle)

    }

    override fun addToWishlist(isAdded: Int, prodId: Int) {
    }
}