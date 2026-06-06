package com.example.test_task.domain.usecase

import com.example.test_task.domain.model.Course
import com.example.test_task.domain.repository.CoursesRepository
import javax.inject.Inject

class ToggleFavoriteCourseUseCase @Inject constructor(
    private val coursesRepository: CoursesRepository
) {
    suspend operator fun invoke(course: Course) {
        if (course.hasLike) {
            coursesRepository.removeCourseFromFavorites(course.id)
        } else {
            coursesRepository.addCourseToFavorites(course.copy(hasLike = true))
        }
    }
}