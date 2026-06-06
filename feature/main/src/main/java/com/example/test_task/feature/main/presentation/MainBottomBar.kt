package com.example.test_task.feature.main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.annotation.DrawableRes
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test_task.feature.main.R

enum class MainTab {
    HOME,
    FAVORITES,
    ACCOUNT
}

@Composable
fun BottomBar(
    selectedTab: MainTab,
    onTabClick: (MainTab) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(36, 37, 42))
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        MainTab.entries.forEach { tab ->
            BottomBarItem(
                tab = tab,
                selected = tab == selectedTab,
                onClick = { onTabClick(tab) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeColor = Color(18, 185, 86)
    val inactiveColor = Color(116, 118, 128)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(width = 64.dp, height = 32.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (selected) Color(50, 51, 58) else Color.Transparent
                )
        ) {
            Icon(
                painter = painterResource(tab.iconRes),
                contentDescription = tab.title,
                tint = if (selected) activeColor else inactiveColor,
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            text = tab.title,
            color = if (selected) activeColor else inactiveColor,
            fontFamily = FontFamily(Font(com.example.test_task.core.ui.R.font.roboto_semibold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        )
    }
}

private val MainTab.title: String
    get() = when (this) {
        MainTab.HOME -> "Главная"
        MainTab.FAVORITES -> "Избранное"
        MainTab.ACCOUNT -> "Аккаунт"
    }

private val MainTab.iconRes: Int
    @DrawableRes
    get() = when (this) {
        MainTab.HOME -> R.drawable.ic_main
        MainTab.FAVORITES -> R.drawable.ic_mark
        MainTab.ACCOUNT -> R.drawable.ic_account
    }
