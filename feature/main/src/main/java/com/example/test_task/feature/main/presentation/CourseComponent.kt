package com.example.test_task.feature.main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test_task.feature.main.R
import com.example.test_task.domain.model.Course
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun Course(
    course: Course,
    onFavoriteClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(328.dp, 236.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(236.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(36, 37, 42))
            ) {
                Column(
                    modifier = Modifier
                        .offset(y = 114.dp)
                        .padding(16.dp)
                        .wrapContentSize()
                ) {
                    Text(
                        text = course.title,
                        fontFamily = FontFamily(Font(com.example.test_task.core.ui.R.font.roboto_medium)),
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        letterSpacing = 0.15.sp,
                        color = Color.White,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = course.text,
                        fontFamily = FontFamily(Font(com.example.test_task.core.ui.R.font.roboto_regular)),
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.4.sp,
                        color = Color(242, 242, 243, 128),
                        modifier = Modifier.height(32.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${course.price} ₽",
                            fontFamily = FontFamily(Font(com.example.test_task.core.ui.R.font.roboto_medium)),
                            fontSize = 16.sp,
                            lineHeight = 18.sp,
                            color = Color.White
                        )

                        Row(
                            modifier = Modifier.wrapContentSize()
                        ) {
                            Text(
                                "Подробнее",
                                fontFamily = FontFamily(Font(com.example.test_task.core.ui.R.font.roboto_semibold)),
                                fontSize = 12.sp,
                                lineHeight = 15.sp,
                                letterSpacing = 0.4.sp,
                                color = Color(18, 185, 86)
                            )
                            Icon(
                                painter = painterResource(R.drawable.arrow_right_short_fill),
                                contentDescription = "arrow",
                                modifier = Modifier.size(16.dp),
                                tint = Color(18, 185, 86)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(114.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(116, 118, 128, 255))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(116, 118, 128)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Course image",
                        fontFamily = FontFamily(Font(com.example.test_task.core.ui.R.font.roboto_medium)),
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.4.sp,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(50, 51, 58).copy(alpha = 0.3f))
                                .border(
                                    width = 1.dp,
                                    color = Color.White.copy(alpha = 0.08f),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(6.dp)
                                .clickable { onFavoriteClick() }
                                .align(Alignment.End)
                        ) {
                            Icon(
                                painter = painterResource(
                                    if (course.hasLike) R.drawable.ic_mark_fill
                                    else R.drawable.ic_mark
                                ),
                                contentDescription = "mark",
                                tint = if (course.hasLike) Color(18, 185, 86) else Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color(50, 51, 58).copy(alpha = 0.3f))
                                    .border(
                                        width = 1.dp,
                                        color = Color.White.copy(alpha = 0.08f),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(vertical = 4.dp, horizontal = 6.dp)
                            )
                            {
                                Row(
                                    modifier = Modifier.wrapContentSize()
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_star),
                                        contentDescription = "star",
                                        tint = Color(18, 185, 86),
                                        modifier = Modifier.size(12.dp)
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = course.rate,
                                        fontFamily = FontFamily(Font(com.example.test_task.core.ui.R.font.roboto_regular)),
                                        fontSize = 12.sp,
                                        lineHeight = 14.sp,
                                        letterSpacing = 0.4.sp,
                                        color = Color.White
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(4.dp))

                            Box(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color(50, 51, 58).copy(alpha = 0.3f))
                                    .border(
                                        width = 1.dp,
                                        color = Color.White.copy(alpha = 0.08f),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(vertical = 4.dp, horizontal = 6.dp)
                            ) {
                                Text(
                                    text = formatCourseDate(course.startDate),
                                    fontFamily = FontFamily(Font(com.example.test_task.core.ui.R.font.roboto_regular)),
                                    fontSize = 12.sp,
                                    lineHeight = 14.sp,
                                    letterSpacing = 0.4.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

private fun formatCourseDate(date: String): String {
    return try {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val outputFormatter = SimpleDateFormat("d MMMM yyyy", Locale.forLanguageTag("ru"))

        val parsedDate = inputFormatter.parse(date)

        if (parsedDate != null) {
            outputFormatter.format(parsedDate)
        } else {
            date
        }
    } catch (exception: Exception) {
        date
    }
}
