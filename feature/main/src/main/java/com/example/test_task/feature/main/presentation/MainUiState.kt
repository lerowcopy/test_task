package com.example.test_task.feature.main.presentation

import com.example.test_task.domain.model.Course

data class MainUiState(
    val courses:                List<Course> = emptyList(),
    val originalCourses:        List<Course> = emptyList(),
    val favoriteCourses:        List<Course> = emptyList(),
    val isLoading:              Boolean = false,
    val errorMessage:           Int? = null,
    val isSortedByPublishDate:  Boolean = false
)