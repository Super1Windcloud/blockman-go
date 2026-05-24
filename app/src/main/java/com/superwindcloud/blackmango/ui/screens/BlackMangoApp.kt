package com.superwindcloud.blackmango.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.superwindcloud.blackmango.ui.navigation.BottomTabHitLayer
import com.superwindcloud.blackmango.ui.navigation.MainTab
import com.superwindcloud.blackmango.ui.navigation.ScreenBackground

@Composable
fun BlackMangoApp() {
    var selectedTab by rememberSaveable { mutableStateOf(MainTab.Home) }

    Surface(modifier = Modifier.fillMaxSize(), color = ScreenBackground) {
        Box(Modifier.fillMaxSize()) {
            ActiveTabPage(selectedTab, modifier = Modifier.fillMaxSize().statusBarsPadding())
            BottomTabHitLayer(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

@Composable
private fun ActiveTabPage(selectedTab: MainTab, modifier: Modifier = Modifier) {
    when (selectedTab) {
        MainTab.Home -> HomeTabPage(modifier)
        MainTab.Rooms -> RoomsTabPage(modifier)
        MainTab.Shop -> ShopTabPage(modifier)
        MainTab.Social -> SocialTabPage(modifier)
        MainTab.Profile -> ProfileTabPage(modifier)
    }
}
