package com.example.test_task.feature.main.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.test_task.feature.main.R

@Composable
fun TopBar() {
    var searchQuery by remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp)
    ) {
        TextField(
            value = searchQuery,
            placeholder = { Text(text = "Search courses...") },
            onValueChange = {searchQuery = it},
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(36, 37, 42),
                unfocusedContainerColor = Color(36, 37, 42),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier.weight(1f),
            leadingIcon = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(4.dp).size(48.dp)
                ){
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = "search courses",
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                    )

                }
            }
        )

        Button(
            onClick = {},
            modifier = Modifier.size(56.dp),
            shape = RoundedCornerShape(28.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(36, 37, 42)
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "Фильтр",
                //tint = Color.White,
                modifier = Modifier.size(24.dp, 24.dp)
            )
        }

    }
}
