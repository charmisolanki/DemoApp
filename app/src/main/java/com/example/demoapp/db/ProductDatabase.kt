package com.example.demoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoapp.models.ProductList
import com.example.demoapp.models.ProductListItem

@Database(entities = [ProductListItem::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao

    companion object {

        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context) : ProductDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        ProductDatabase::class.java,
                        "productDB").build()
                }
            }
            return INSTANCE!!

        }
    }
}