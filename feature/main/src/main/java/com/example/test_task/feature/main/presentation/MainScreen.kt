package com.example.test_task.feature.main.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.test_task.feature.main.R
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(MainTab.HOME) }
    val viewModel: MainViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsState()
    val displayedCourses = if (selectedTab == MainTab.FAVORITES) {
        uiState.favoriteCourses
    } else {
        uiState.courses
    }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            BottomBar(
                selectedTab = selectedTab,
                onTabClick = { selectedTab = it }
            )
        },
        containerColor = Color(21, 21, 21),
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            if (selectedTab == MainTab.HOME) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(16.dp)
                        .wrapContentSize()
                        .clickable {
                            viewModel.toggleSortByPublishDate()

                            coroutineScope.launch {
                                listState.animateScrollToItem(0)
                            }
                        }
                ) {
                    Text(
                        text = stringResource(R.string.main_sort_by_publish_date),
                        fontFamily = FontFamily(Font(com.example.test_task.core.ui.R.font.roboto_medium)),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.1.sp,
                        color = Color(18, 185, 86),
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        painter = painterResource(R.drawable.ic_sort),
                        contentDescription = "sort",
                        tint = Color(18, 185, 86)
                    )
                }
            }else{
                Spacer(modifier = Modifier.height(52.dp))
            }
            when {
                uiState.isLoading -> {
                    Text(
                        stringResource(R.string.main_loading),
                        color = Color.White,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                }

                uiState.errorMessage != null -> {
                    Text(
                        text = stringResource(uiState.errorMessage!!),
                        color = Color.Red,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                }

                else -> {
                    when (selectedTab) {
                        MainTab.ACCOUNT -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.main_account_screen),
                                    color = Color.White
                                )
                            }
                        }

                        else -> {
                            if (selectedTab == MainTab.FAVORITES && displayedCourses.isEmpty()) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(R.string.main_empty_favorites),
                                        color = Color.White
                                    )
                                }
                            } else {
                                LazyColumn(
                                    state = listState,
                                    verticalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    items(
                                        items = displayedCourses,
                                        key = { course -> course.id }
                                    ) { course ->
                                        Course(
                                            course = course,
                                            onFavoriteClick = {
                                                viewModel.toggleFavorite(course)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
