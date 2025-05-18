package com.amms.week8practice.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

/**
 * Pantalla de menú principal.
 * - Ofrece botones para navegar a cada ejercicio.
 */
@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()             // Ocupa toda la pantalla
            .padding(16.dp),           // Padding interior
        verticalArrangement = Arrangement.Center,        // Centrar verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centrar horizontalmente
    ) {
        // Título
        Text(
            text = "Selecciona un ejercicio",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para ir al ejercicio de adivinar número
        Button(
            onClick = { navController.navigate("guess") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ejercicio 1: Adivina el número")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para ir al ejercicio de tabla de multiplicar
        Button(
            onClick = { navController.navigate("multiply") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ejercicio 2: Tabla de multiplicar")
        }
    }
}