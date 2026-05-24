package com.superwindcloud.blackmango.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SocialTabPage(modifier: Modifier = Modifier) {
    ScrollableTabPage(title = "社交", subtitle = "好友动态和一起玩的房间", modifier = modifier) {
        items(6) { index ->
            SocialFeedCard(
                name = listOf("Mango", "Alex", "Nina", "Cloud", "Jason", "Luna")[index],
                text =
                    listOf(
                        "正在组队挑战起床战争",
                        "发布了新的跑酷成绩",
                        "收藏了空岛生存地图",
                        "邀请你加入交友大厅",
                        "刚刚通关迷雾逃生",
                        "创建了新的 PVP 房间",
                    )[index],
            )
        }
        categorySections[1].let { gameSection(it) }
    }
}

@Composable
private fun SocialFeedCard(name: String, text: String, modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = name.take(1),
            modifier =
                Modifier.background(Color(0xFF151A22), RoundedCornerShape(8.dp))
                    .padding(horizontal = 14.dp, vertical = 10.dp),
            color = Color.White,
            fontWeight = FontWeight.Black,
        )
        Column {
            Text(name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text, color = Color(0xFF69717C))
        }
    }
}
