package com.example.demoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.ProductClickListener
import com.example.demoapp.R
import com.example.demoapp.models.ProductListItem

class WishListAdapter(val products: List<ProductListItem>, val clickListener: ProductClickListener) : RecyclerView.Adapter<WishListAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.cbWishlist.visibility = View.GONE
        holder.tvTitle.text = products[position].title
        holder.tvDescription.text = products[position].description

        holder.itemView.setOnClickListener {
            clickListener.viewDetail(products[position].id)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        var tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        var cbWishlist = itemView.findViewById<CheckBox>(R.id.cb_wishList)
    }
}