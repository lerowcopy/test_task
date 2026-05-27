package com.example.test_task.presentation.main

import com.example.test_task.domain.model.Course

data class MainUiState(
    val courses:                List<Course> = emptyList(),
    val originalCourses:        List<Course> = emptyList(),
    val favoriteCourses:        List<Course> = emptyList(),
    val isLoading:              Boolean = false,
    val errorMessage:           String? = null,
    val isSortedByPublishDate:  Boolean = false
)