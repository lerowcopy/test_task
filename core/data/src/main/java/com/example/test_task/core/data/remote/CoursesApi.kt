package com.example.test_task.core.data.remote

import retrofit2.http.GET

interface CoursesApi {

    @GET(ApiConstants.COURSES_ENDPOINT)
    suspend fun getCourses(): CoursesResponse
}