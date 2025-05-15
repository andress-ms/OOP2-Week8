package com.amms.week8practice.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Selecciona un ejercicio", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = { navController.navigate("guess") }) {
            Text("1. Adivina el número")
        }
        Button(onClick = { navController.navigate("table") }) {
            Text("2. Tabla de multiplicar")
        }
    }
}