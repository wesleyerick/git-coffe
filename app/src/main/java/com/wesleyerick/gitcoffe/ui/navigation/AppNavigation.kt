package com.wesleyerick.gitcoffe.ui.navigation

enum class Screen(val withArgs: String = "") {
    SPLASH,
    POPULAR,
    PR("PR/{creator}/{repo}"),
}
sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Popular : NavigationItem(Screen.POPULAR.name)
    object PR : NavigationItem(Screen.PR.withArgs)
}