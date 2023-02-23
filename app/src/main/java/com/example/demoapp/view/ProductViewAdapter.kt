package com.example.demoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.demoapp.ProductClickListener
import com.example.demoapp.R
import com.example.demoapp.models.ProductList
import com.example.demoapp.models.ProductListItem

class ProductViewAdapter(val clickListener: ProductClickListener) : RecyclerView.Adapter<ProductViewAdapter.ProductViewHolder>() {

    private var products = ArrayList<ProductListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.tvTitle.text = products[position].title
        holder.tvDescription.text = products[position].description
        val isAddedToWishList = products[position].isWishListed == 1
        holder.cbWishlist.isChecked = isAddedToWishList

        holder.cbWishlist.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked) clickListener.addToWishlist(1, products[position].id)
            else clickListener.addToWishlist(0, products[position].id)
        }

        holder.itemView.setOnClickListener {
            clickListener.viewDetail(products[position].id)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateProducts(productList : List<ProductListItem>){
        this.products.clear()
        this.products.addAll(productList)
        notifyDataSetChanged()
    }
    class ProductViewHolder(itemView: View) : ViewHolder(itemView){
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        var tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        var cbWishlist = itemView.findViewById<CheckBox>(R.id.cb_wishList)
    }
}

