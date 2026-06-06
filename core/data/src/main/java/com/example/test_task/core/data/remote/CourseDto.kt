package com.example.test_task.core.data.remote

data class CoursesResponse(
    val courses: List<CourseDto>
)

data class CourseDto(
    val id:             Int,
    val title:          String,
    val text:           String,
    val price:          String,
    val rate:           String,
    val startDate:      String,
    val hasLike:        Boolean,
    val publishDate:    String
)