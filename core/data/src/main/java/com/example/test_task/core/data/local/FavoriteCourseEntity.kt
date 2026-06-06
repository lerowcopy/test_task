package com.example.test_task.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_courses")
data class FavoriteCourseEntity (
    @PrimaryKey
    val id:             Int,
    val title:          String,
    val text:           String,
    val price:          String,
    val rate:           String,
    val startDate:      String,
    val hasLike:        Boolean,
    val publishDate:    String

)