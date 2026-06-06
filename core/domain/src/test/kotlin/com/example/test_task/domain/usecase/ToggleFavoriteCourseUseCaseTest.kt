package com.example.test_task.domain.usecase

import com.example.test_task.domain.model.Course
import com.example.test_task.domain.repository.CoursesRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ToggleFavoriteCourseUseCaseTest {

    private val repository = FakeCoursesRepository()
    private val useCase = ToggleFavoriteCourseUseCase(repository)

    @Test
    fun `adds course to favorite when course is not favorite`() = runBlocking {
        val course = testCourse(hasLike = false)

        useCase(course)

        assertEquals(course.copy(hasLike = true), repository.addedCourse)
        assertNull(repository.removedCourseId)
    }

    @Test
    fun `remove course from favorite when course is favorite`() = runBlocking {
        val course = testCourse(hasLike = true)

        useCase(course)

        assertEquals(course.id, repository.removedCourseId)
        assertNull(repository.addedCourse)
    }

    private fun testCourse(hasLike: Boolean): Course {
        return Course(
            id = 1,
            title = "Kotlin",
            text = "Course description",
            price = "1000",
            rate = "4.9",
            startDate = "2024-05-22",
            hasLike = hasLike,
            publishDate = "2024-05-22"
        )
    }

    private class FakeCoursesRepository : CoursesRepository {
        var addedCourse: Course? = null
        var removedCourseId: Int? = null

        override suspend fun getCourses(): List<Course> = emptyList()

        override fun getFavoriteCourses(): Flow<List<Course>> = emptyFlow()

        override suspend fun addCourseToFavorites(course: Course) {
            addedCourse = course
        }

        override suspend fun removeCourseFromFavorites(courseId: Int) {
            removedCourseId = courseId
        }
    }
}