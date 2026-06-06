package com.example.test_task.domain.usecase

import com.example.test_task.domain.model.Course
import com.example.test_task.domain.repository.CoursesRepository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val coursesRepository: CoursesRepository
) {
    suspend operator fun invoke(): List<Course> {
        return coursesRepository.getCourses()
    }
}