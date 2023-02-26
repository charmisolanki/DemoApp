package com.example.demoapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.demoapp.ProductClickListener
import com.example.demoapp.databinding.ItemProductViewBinding
import com.example.demoapp.models.ProductListItem

class ProductViewAdapter(private val clickListener: ProductClickListener) :
    RecyclerView.Adapter<ProductViewAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductViewBinding) : ViewHolder(binding.root)

    private var products = ArrayList<ProductListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ItemProductViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = products[position]

        (holder.binding).apply {
            tvTitle.text = product.title
            tvDescription.text = product.description
            cbWishList.isChecked = product.isWishListed == 1

            cbWishList.setOnCheckedChangeListener { checkBox, isChecked ->
                if (isChecked) clickListener.addToWishlist(1, products[position].id)
                else clickListener.addToWishlist(0, products[position].id)
            }
        }

        holder.itemView.setOnClickListener {
            clickListener.viewDetail(products[position].id)
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateProducts(productList: List<ProductListItem>) {
        this.products.clear()
        this.products.addAll(productList)
        notifyDataSetChanged()
    }

}

