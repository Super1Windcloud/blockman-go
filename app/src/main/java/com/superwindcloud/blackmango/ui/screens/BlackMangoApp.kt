package com.superwindcloud.blackmango.ui.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.superwindcloud.blackmango.ui.navigation.AppRoute
import com.superwindcloud.blackmango.ui.navigation.BottomTabHitLayer
import com.superwindcloud.blackmango.ui.navigation.ScreenBackground
import com.superwindcloud.blackmango.ui.navigation.route
import com.superwindcloud.blackmango.ui.navigation.toMainTabOrNull

@Composable
fun BlackMangoApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentTab = backStackEntry?.destination?.route.toMainTabOrNull()
    val navigatePlaceholder: (String) -> Unit = { title ->
        navController.navigate(AppRoute.Placeholder.createRoute(title))
    }

    Surface(modifier = Modifier.fillMaxSize(), color = ScreenBackground) {
        Box(Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = AppRoute.Home.route,
                modifier = Modifier.fillMaxSize(),
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None },
            ) {
                composable(AppRoute.Home.route) {
                    HomeTabPage(
                        modifier = Modifier.fillMaxSize().statusBarsPadding(),
                        onNavigate = navigatePlaceholder,
                    )
                }
                composable(AppRoute.Rooms.route) {
                    RoomsTabPage(
                        modifier = Modifier.fillMaxSize().statusBarsPadding(),
                        onNavigate = navigatePlaceholder,
                    )
                }
                composable(AppRoute.Shop.route) {
                    ShopTabPage(
                        modifier = Modifier.fillMaxSize(),
                        onNavigate = navigatePlaceholder,
                        onFullscreenClick = {
                            navController.navigate(AppRoute.ShopModelFullscreen.route)
                        },
                    )
                }
                composable(AppRoute.ShopModelFullscreen.route) {
                    ShopTabPage(
                        modifier = Modifier.fillMaxSize(),
                        modelFullscreen = true,
                        onNavigate = navigatePlaceholder,
                        onFullscreenClick = { navController.popBackStack() },
                    )
                }
                composable(AppRoute.Social.route) {
                    SocialTabPage(
                        modifier = Modifier.fillMaxSize().statusBarsPadding(),
                        onNavigate = navigatePlaceholder,
                    )
                }
                composable(AppRoute.Profile.route) {
                    ProfileTabPage(
                        modifier = Modifier.fillMaxSize().statusBarsPadding(),
                        onNavigate = navigatePlaceholder,
                    )
                }
                composable(
                    route = AppRoute.Placeholder.route,
                    arguments =
                        listOf(
                            navArgument(AppRoute.Placeholder.TITLE_ARG) {
                                type = NavType.StringType
                            }
                        ),
                ) { entry ->
                    PlaceholderRoutePage(
                        title =
                            entry.arguments?.getString(AppRoute.Placeholder.TITLE_ARG) ?: "占位页面",
                        onBackClick = { navController.popBackStack() },
                        modifier = Modifier.fillMaxSize().statusBarsPadding(),
                    )
                }
            }
            currentTab?.let { selectedTab ->
                BottomTabHitLayer(
                    selectedTab = selectedTab,
                    onTabSelected = { tab ->
                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    }
}
