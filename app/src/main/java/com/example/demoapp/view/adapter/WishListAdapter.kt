package com.example.demoapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.ProductClickListener
import com.example.demoapp.R
import com.example.demoapp.databinding.ItemProductViewBinding
import com.example.demoapp.models.ProductListItem

class WishListAdapter(private val context: Context?, private val products: List<ProductListItem>, val clickListener: ProductClickListener) : RecyclerView.Adapter<WishListAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        (holder.binding).apply {
            cbWishList.visibility = View.GONE
            tvTitle.text = product.title
            val price = "${context?.getString(R.string.rupee_symbol)} ${product?.price}"
            tvTitle.text = price
            ratingBar.rating = product.rating.rate.toFloat()
        }


        holder.itemView.setOnClickListener {
            clickListener.viewDetail(products[position].id)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(val binding: ItemProductViewBinding) : RecyclerView.ViewHolder(binding.root)
}