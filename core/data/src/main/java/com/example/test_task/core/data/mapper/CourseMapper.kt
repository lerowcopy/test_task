package com.example.test_task.data.mapper

import com.example.test_task.data.local.FavoriteCourseEntity
import com.example.test_task.data.remote.CourseDto
import com.example.test_task.domain.model.Course

fun CourseDto.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}

fun Course.toFavoriteEntity(): FavoriteCourseEntity {
    return FavoriteCourseEntity(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = true,
        publishDate = publishDate
    )
}

fun FavoriteCourseEntity.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = true,
        publishDate = publishDate
    )
}