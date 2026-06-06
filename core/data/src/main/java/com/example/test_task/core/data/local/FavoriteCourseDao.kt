package com.example.test_task.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCourseDao {

    @Query("SELECT * FROM favorite_courses")
    fun getFavoriteCourses(): Flow<List<FavoriteCourseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCourse(course: FavoriteCourseEntity)

    @Delete
    suspend fun deleteFavoriteCourse(course: FavoriteCourseEntity)

    @Query("DELETE FROM favorite_courses WHERE id = :courseId")
    suspend fun deleteFavoriteCourseById(courseId: Int)
}