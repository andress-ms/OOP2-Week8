package com.amms.week8practice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amms.week8practice.ui.screens.HomeScreen
import com.amms.week8practice.ui.screens.GuessNumberScreen
import com.amms.week8practice.ui.screens.MultiplicationScreen

/**
 * Define las rutas (destinos) de la aplicación.
 * - startDestination = "home"
 * - cada pantalla se identifica con una ruta String.
 */
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController  = navController,
        startDestination = "home"
    ) {
        // Pantalla inicial con menú de ejercicios
        composable("home") {
            HomeScreen(navController)
        }
        // Ejercicio 1: Adivinar número
        composable("guess") {
            GuessNumberScreen(navController)
        }
        // Ejercicio 2: Tabla de multiplicar
        composable("multiply") {
            MultiplicationScreen(navController)
        }
    }
}