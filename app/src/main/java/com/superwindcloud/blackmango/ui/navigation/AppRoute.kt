package com.superwindcloud.blackmango.ui.navigation

import android.net.Uri

sealed class AppRoute(val route: String) {
    data object Home : AppRoute("home")

    data object Rooms : AppRoute("rooms")

    data object Shop : AppRoute("shop")

    data object ShopModelFullscreen : AppRoute("shop/model/fullscreen")

    data object Social : AppRoute("social")

    data object Profile : AppRoute("profile")

    data object Js : AppRoute("js")

    data object Placeholder : AppRoute("placeholder/{title}") {
        const val TITLE_ARG = "title"

        fun createRoute(title: String): String = "placeholder/${Uri.encode(title)}"
    }
}

val MainTab.route: String
    get() =
        when (this) {
            MainTab.Home -> AppRoute.Home.route
            MainTab.Rooms -> AppRoute.Rooms.route
            MainTab.Shop -> AppRoute.Shop.route
            MainTab.Social -> AppRoute.Social.route
            MainTab.Profile -> AppRoute.Profile.route
            MainTab.Js -> AppRoute.Js.route
        }

fun String?.toMainTabOrNull(): MainTab? =
    when (this) {
        AppRoute.Home.route -> MainTab.Home
        AppRoute.Rooms.route -> MainTab.Rooms
        AppRoute.Shop.route -> MainTab.Shop
        AppRoute.Social.route -> MainTab.Social
        AppRoute.Profile.route -> MainTab.Profile
        AppRoute.Js.route -> MainTab.Js
        else -> null
    }
