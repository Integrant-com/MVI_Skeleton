package com.example.skeleton.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skeleton.features.sigin.view.SignInScreen


@Composable
fun SkeletonNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SkeletonScreens.SignInScreen.name) {
        composable(SkeletonScreens.SignInScreen.name) {
            SignInScreen(navController = navController)
        }
    }
}