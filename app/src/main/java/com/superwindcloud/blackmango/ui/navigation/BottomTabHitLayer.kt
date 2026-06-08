package com.superwindcloud.blackmango.ui.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomTabHitLayer(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier.navigationBarsPadding(),
        containerColor = Color.White.copy(alpha = 0.96f),
    ) {
        MainTab.entries.forEach { tab ->
            NavigationBarItem(
                selected = tab == selectedTab,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                    )
                },
                label = { Text(tab.label) },
            )
        }
    }
}

val ScreenBackground = Color(0xFFF5F6F8)

private val MainTab.label: String
    get() =
        when (this) {
            MainTab.Home -> "Home"
            MainTab.Rooms -> "Rooms"
            MainTab.Shop -> "Shop"
            MainTab.Social -> "Social"
            MainTab.Profile -> "Profile"
            MainTab.Js -> "JS"
        }

private val MainTab.icon: ImageVector
    get() =
        when (this) {
            MainTab.Home -> Icons.Filled.Home
            MainTab.Rooms -> Icons.Filled.ViewInAr
            MainTab.Shop -> Icons.Filled.Storefront
            MainTab.Social -> Icons.Filled.People
            MainTab.Profile -> Icons.Filled.AccountCircle
            MainTab.Js -> Icons.Filled.Code
        }
