package com.wesleyerick.gitcoffe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wesleyerick.gitcoffe.ui.screen.SplashScreen
import com.wesleyerick.gitcoffe.ui.screen.popular.presentation.PopularScreen
import com.wesleyerick.gitcoffe.ui.screen.pr.presentation.PullRequestScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Popular.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationItem.Popular.route) {
            PopularScreen(navController)
        }
        composable(
            route = NavigationItem.PR.route
        ) {
            val creator = it.arguments?.getString("creator").orEmpty()
            val repo = it.arguments?.getString("repo").orEmpty()
            PullRequestScreen(
                navController = navController,
                creator = creator,
                repo = repo
            )
        }
    }
}