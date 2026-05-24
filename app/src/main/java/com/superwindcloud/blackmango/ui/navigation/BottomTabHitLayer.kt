package com.superwindcloud.blackmango.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BottomTabHitLayer(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .background(Color.White)
                .navigationBarsPadding()
                .height(72.dp)
                .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        MainTab.entries.forEach { tab ->
            val interactionSource = remember(tab) { MutableInteractionSource() }
            Box(
                modifier =
                    Modifier.size(width = 66.dp, height = 56.dp)
                        .background(
                            if (tab == selectedTab) Color(0xFF151A22) else Color.Transparent,
                            RoundedCornerShape(8.dp),
                        )
                        .clickable(
                            enabled = tab != selectedTab,
                            role = Role.Tab,
                            indication = null,
                            interactionSource = interactionSource,
                        ) {
                            onTabSelected(tab)
                        },
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = tab.symbol,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (tab == selectedTab) Color.White else Color(0xFF69717C),
                    )
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (tab == selectedTab) Color.White else Color(0xFF69717C),
                    )
                }
            }
        }
    }
}

val ScreenBackground = Color(0xFFF5F6F8)

private val MainTab.label: String
    get() =
        when (this) {
            MainTab.Home -> "首页"
            MainTab.Rooms -> "房间"
            MainTab.Shop -> "商店"
            MainTab.Social -> "社交"
            MainTab.Profile -> "我的"
        }

private val MainTab.symbol: String
    get() =
        when (this) {
            MainTab.Home -> "H"
            MainTab.Rooms -> "R"
            MainTab.Shop -> "S"
            MainTab.Social -> "C"
            MainTab.Profile -> "M"
        }
