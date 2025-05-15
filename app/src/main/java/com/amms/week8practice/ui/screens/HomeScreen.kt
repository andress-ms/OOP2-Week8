// HomeScreen.kt
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

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Selecciona un ejercicio", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate("guess") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ejercicio 1: Adivina el número")
        }
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { navController.navigate("multiply") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ejercicio 2: Tabla de multiplicar")
        }
    }
}