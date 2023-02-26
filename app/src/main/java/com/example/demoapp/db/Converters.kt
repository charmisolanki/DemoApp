package com.example.demoapp.db

import androidx.room.TypeConverter
import com.example.demoapp.models.Rating
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun ratingToString(rating: Rating): String {
        val gson = Gson()
        return gson.toJson(rating)
    }

    @TypeConverter
    fun stringToRating(str: String): Rating{
        val gson = Gson()
        return gson.fromJson(str, Rating::class.java)
    }
}