package com.example.demoapp.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductListItem(
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String,
    val rating: Rating,
    var isWishListed: Int    // 1 - added , - 0 not added
)