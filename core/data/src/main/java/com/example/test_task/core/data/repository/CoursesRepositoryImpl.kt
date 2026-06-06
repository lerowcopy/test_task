package com.example.test_task.data.repository

import com.example.test_task.data.local.FavoriteCourseDao
import com.example.test_task.data.mapper.toDomain
import com.example.test_task.data.mapper.toFavoriteEntity
import com.example.test_task.data.remote.CoursesApi
import com.example.test_task.domain.model.Course
import com.example.test_task.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val coursesApi: CoursesApi,
    private val favoriteCourseDao: FavoriteCourseDao
) : CoursesRepository {

    override suspend fun getCourses(): List<Course> {
        return coursesApi.getCourses().courses.map { it.toDomain() }
    }

    override fun getFavoriteCourses(): Flow<List<Course>> {
        return favoriteCourseDao.getFavoriteCourses()
            .map { favoriteCourses ->
                favoriteCourses.map { course -> course.toDomain() }
        }
    }

    override suspend fun addCourseToFavorites(course: Course) {
        favoriteCourseDao.insertFavoriteCourse(course.toFavoriteEntity())
    }

    override suspend fun removeCourseFromFavorites(courseId: Int) {
        favoriteCourseDao.deleteFavoriteCourseById(courseId)
    }
}