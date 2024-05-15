package com.wesleyerick.gitcoffe.ui.navigation

enum class Screen {
    SPLASH,
    POPULAR,
    PR,
}
sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Popular : NavigationItem(Screen.POPULAR.name)
    object PR : NavigationItem(Screen.PR.name)
}