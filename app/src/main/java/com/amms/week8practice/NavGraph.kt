// NavGraph.kt
package com.amms.week8practice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amms.week8practice.ui.screens.HomeScreen
import com.amms.week8practice.ui.screens.GuessNumberScreen
import com.amms.week8practice.ui.screens.MultiplicationScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home")     { HomeScreen(navController) }
        composable("guess")    { GuessNumberScreen(navController) }
        composable("multiply") { MultiplicationScreen(navController) }
    }
}