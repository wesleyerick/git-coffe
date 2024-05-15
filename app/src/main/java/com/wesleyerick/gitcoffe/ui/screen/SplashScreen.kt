package com.wesleyerick.gitcoffe.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.wesleyerick.gitcoffe.ui.navigation.NavigationItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "SplashScreen")

        LaunchedEffect(Dispatchers.Default) {
            delay(3000)
            navController.navigate(NavigationItem.Popular.route)
        }
    }
}