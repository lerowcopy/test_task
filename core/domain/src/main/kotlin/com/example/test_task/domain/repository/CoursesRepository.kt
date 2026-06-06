package com.example.test_task.domain.repository

import com.example.test_task.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {

    suspend fun getCourses(): List<Course>

    fun getFavoriteCourses(): Flow<List<Course>>

    suspend fun addCourseToFavorites(course: Course)

    suspend fun removeCourseFromFavorites(courseId: Int)
}