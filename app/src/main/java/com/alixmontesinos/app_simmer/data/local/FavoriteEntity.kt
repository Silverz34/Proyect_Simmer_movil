package com.alixmontesinos.app_simmer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val imageUrl: String,
    val imageRes: Int?,
    val time: String,
    val difficulty: String,
    val calories: String,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>
)
