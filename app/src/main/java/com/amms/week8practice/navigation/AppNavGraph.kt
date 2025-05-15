package com.amms.week8practice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amms.week8practice.ui.screens.MenuScreen
import com.amms.week8practice.ui.screens.GuessNumberScreen
import com.amms.week8practice.ui.screens.MultiplicationTableScreen


@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {MenuScreen(navController)}
        composable("adivina el numero") {GuessNumberScreen(navController)}
        composable("tabla de multiplicar") {MultiplicationTableScreen(navController)}
    }
}