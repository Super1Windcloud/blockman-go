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
fun ShopTabPage(modifier: Modifier = Modifier) {
    ScrollableTabPage(title = "商店", subtitle = "头像、装扮和热门道具", modifier = modifier) {
        item { ShopBanner() }
        homeSections.first().games.take(4).chunked(2).forEach { row ->
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    row.forEach { game ->
                        GameCardItem(game = game, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
        categorySections.take(2).forEach { gameSection(it) }
    }
}

@Composable
private fun ShopBanner() {
    Column(
        modifier =
            Modifier.fillMaxWidth()
                .background(Color(0xFF151A22), RoundedCornerShape(8.dp))
                .padding(16.dp)
    ) {
        Text("限时精选", color = Color(0xFFFFD166), fontWeight = FontWeight.Bold)
        Text(
            "本周热门角色皮肤",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
        )
        Text("搭配房间玩法使用，动态内容可继续向下滚动浏览", color = Color(0xFFD8DEE8))
    }
}
