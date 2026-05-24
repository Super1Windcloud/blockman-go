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
fun ProfileTabPage(modifier: Modifier = Modifier) {
    ScrollableTabPage(title = "我的", subtitle = "资料、收藏和最近游玩", modifier = modifier) {
        item { ProfileSummary() }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                StatCard("收藏", "26", Modifier.weight(1f))
                StatCard("关注", "148", Modifier.weight(1f))
                StatCard("成就", "39", Modifier.weight(1f))
            }
        }
        gameSection(GameSection("最近游玩", homeSections.first().games.take(4)))
        gameSection(GameSection("我的收藏", categorySections.last().games))
    }
}

@Composable
private fun ProfileSummary() {
    Column(
        modifier =
            Modifier.fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            "BlackMango 玩家",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
        )
        Text("Lv.18 · 沙盒冒险家", color = Color(0xFF69717C))
    }
}

@Composable
private fun StatCard(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(Color.White, RoundedCornerShape(8.dp)).padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
        Text(label, color = Color(0xFF69717C), style = MaterialTheme.typography.bodySmall)
    }
}
