package com.superwindcloud.blackmango.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.superwindcloud.blackmango.R

@Composable
fun BottomTabHitLayer(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth().navigationBarsPadding().height(72.dp)) {
        Image(
            painter = painterResource(selectedTab.navBarImage),
            contentDescription = null,
            modifier =
                Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .aspectRatio(selectedTab.navBarAspectRatio),
            contentScale = ContentScale.Fit,
        )
        Row(
            modifier = Modifier.fillMaxSize(),
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
}

val ScreenBackground = Color(0xFFF5F6F8)

private val MainTab.navBarImage: Int
    @DrawableRes
    get() =
        when (this) {
            MainTab.Home -> R.drawable.nav_bar_home
            MainTab.Rooms -> R.drawable.nav_bar_rooms
            MainTab.Shop -> R.drawable.nav_bar_shop
            MainTab.Social -> R.drawable.nav_bar_social
            MainTab.Profile -> R.drawable.nav_bar_profile
        }

private val MainTab.navBarAspectRatio: Float
    get() =
        when (this) {
            MainTab.Home -> 1080f / 146f
            MainTab.Rooms -> 1080f / 150f
            MainTab.Shop -> 1080f / 151f
            MainTab.Social -> 1080f / 150f
            MainTab.Profile -> 1080f / 150f
        }
