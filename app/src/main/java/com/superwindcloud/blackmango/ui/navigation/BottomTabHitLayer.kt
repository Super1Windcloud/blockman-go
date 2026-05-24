package com.superwindcloud.blackmango.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun BottomTabHitLayer(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().navigationBarsPadding().height(72.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        MainTab.entries.forEach { tab ->
            val interactionSource = remember(tab) { MutableInteractionSource() }
            Box(
                modifier =
                    Modifier.size(width = 72.dp, height = 64.dp).clickable(
                        enabled = tab != selectedTab,
                        role = Role.Tab,
                        indication = null,
                        interactionSource = interactionSource,
                    ) {
                        onTabSelected(tab)
                    }
            )
        }
    }
}

val ScreenBackground = Color(0xFFF5F6F8)
