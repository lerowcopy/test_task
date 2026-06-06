package com.example.test_task.core.data.di

import com.example.test_task.core.data.repository.CoursesRepositoryImpl
import com.example.test_task.domain.repository.CoursesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoursesRepository(impl: CoursesRepositoryImpl): CoursesRepository
}