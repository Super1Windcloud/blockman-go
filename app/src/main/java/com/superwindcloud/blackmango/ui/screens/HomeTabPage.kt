package com.superwindcloud.blackmango.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeTabPage(modifier: Modifier = Modifier) {
    ScrollableTabPage(title = "Black Mango", subtitle = "发现正在热玩的沙盒游戏", modifier = modifier) {
        item {
            Text(
                text = "搜索游戏、房间或好友",
                modifier =
                    Modifier.fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                color = Color(0xFF8A9099),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                listOf("推荐", "PVP", "生存", "跑酷").forEachIndexed { index, label ->
                    FilterChip(
                        selected = index == 0,
                        onClick = {},
                        label = { Text(label, fontWeight = FontWeight.SemiBold) },
                        shape = RoundedCornerShape(8.dp),
                        colors =
                            FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(0xFF151A22),
                                selectedLabelColor = Color.White,
                                containerColor = Color.White,
                            ),
                    )
                }
            }
        }
        item { HeroGameCard(homeSections.first().games.first()) }
        homeSections.forEach { section -> gameSection(section) }
    }
}
