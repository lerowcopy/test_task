package com.example.test_task.feature.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task.domain.model.Course
import com.example.test_task.domain.repository.CoursesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coursesRepository: CoursesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState(isLoading = true))
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
        observeFavoriteCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            try {
                val favoriteIds = _uiState.value.favoriteCourses.map { course -> course.id }.toSet()
                val courses = coursesRepository.getCourses().map { course ->
                    course.copy(hasLike = course.id in favoriteIds)
                }

                _uiState.value = _uiState.value.copy(
                    courses = courses,
                    originalCourses = courses,
                    isLoading = false,
                    isSortedByPublishDate = false
                )
            } catch (exception: Exception) {
                if (exception is CancellationException) throw exception
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = exception.message ?: "Не удалось загрузить курсы"
                )
            }
        }
    }

    fun toggleSortByPublishDate() {
        val currentState = _uiState.value

        _uiState.value = if (currentState.isSortedByPublishDate) {
            currentState.copy(
                courses = currentState.originalCourses,
                isSortedByPublishDate = false
            )
        } else {
            currentState.copy(
                courses = currentState.courses.sortedByDescending { course ->
                    course.publishDate
                },
                isSortedByPublishDate = true
            )
        }
    }

    private fun observeFavoriteCourses() {
        viewModelScope.launch {
            coursesRepository.getFavoriteCourses().collect { favoriteCourses ->
                val favoriteIds = favoriteCourses.map { course -> course.id }.toSet()

                _uiState.value = _uiState.value.copy(
                    favoriteCourses = favoriteCourses,
                    courses = _uiState.value.courses.map { course ->
                        course.copy(hasLike = course.id in favoriteIds)
                    },
                    originalCourses = _uiState.value.originalCourses.map { course ->
                        course.copy(hasLike = course.id in favoriteIds)
                    }
                )
            }
        }
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            if (course.hasLike) {
                coursesRepository.removeCourseFromFavorites(course.id)
            } else {
                coursesRepository.addCourseToFavorites(course.copy(hasLike = true))
            }
        }
    }
}
