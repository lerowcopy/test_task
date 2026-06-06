package com.example.test_task.domain.usecase

import com.example.test_task.domain.model.Course
import com.example.test_task.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteCoursesUseCase @Inject constructor(
    private val coursesRepository: CoursesRepository
) {

    operator fun invoke(): Flow<List<Course>> {
        return coursesRepository.getFavoriteCourses()
    }
}