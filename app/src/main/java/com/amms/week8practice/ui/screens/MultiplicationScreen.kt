// MultiplicationScreen.kt
package com.amms.week8practice.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MultiplicationScreen(navController: NavHostController) {
    var baseInput   by remember { mutableStateOf("") }
    var tableLines  by remember { mutableStateOf(listOf<String>()) }
    var errorMsg    by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tabla de multiplicar", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = baseInput,
            onValueChange = { baseInput = it },
            label = { Text("Número base") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val base = baseInput.toIntOrNull()
                if (base == null) {
                    errorMsg   = "Ingresa un número válido"
                    tableLines = emptyList()
                } else {
                    errorMsg   = ""
                    tableLines = (1..12).map { "$base × $it = ${base * it}" }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generar tabla")
        }

        if (errorMsg.isNotEmpty()) {
            Text(errorMsg, color = MaterialTheme.colorScheme.error)
        }

        // Mostrar cada línea de la tabla
        tableLines.forEach { line ->
            Text(line, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(Modifier.weight(1f))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al menú")
        }
    }
}