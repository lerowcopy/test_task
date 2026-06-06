package com.example.test_task.core.data.di

import android.content.Context
import androidx.room.Room
import com.example.test_task.core.data.local.AppDatabase
import com.example.test_task.core.data.local.FavoriteCourseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "courses_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteCourseDao(
        database: AppDatabase
    ): FavoriteCourseDao{
        return database.favoriteCourseDao()
    }
}