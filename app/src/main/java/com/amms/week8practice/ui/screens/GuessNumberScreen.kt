// GuessNumberScreen.kt
package com.amms.week8practice.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun GuessNumberScreen(navController: NavHostController) {
    // Estado: número secreto, intento del usuario, mensaje de resultado y error de validación
    var secretNumber by remember { mutableStateOf((1..10).random()) }
    var attempt      by remember { mutableStateOf("") }
    var resultMsg    by remember { mutableStateOf("") }
    var inputError   by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Adivina el número (1–10)", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = attempt,
            onValueChange = { new ->
                // Filtrar para que solo queden dígitos
                val filtered = new.filter { it.isDigit() }
                attempt = filtered

                // Validar rango si hay valor numérico
                inputError = when {
                    filtered.isEmpty() -> null
                    else -> {
                        val n = filtered.toIntOrNull()
                        if (n == null || n !in 1..10) "Ingresa un número entre 1 y 10" else null
                    }
                }
            },
            label = { Text("Tu intento") },
            isError = inputError != null,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        if (inputError != null) {
            Text(
                text = inputError!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Button(
            onClick = {
                val guess = attempt.toIntOrNull()
                resultMsg = when {
                    guess == null                        -> "Ingresa un número válido"
                    guess !in 1..10                      -> "El número debe estar entre 1 y 10"
                    guess == secretNumber                -> "¡Correcto!"
                    else                                 -> "Intenta de nuevo"
                }
            },
            enabled = inputError == null && attempt.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Comprobar")
        }

        Button(
            onClick = {
                secretNumber = (1..10).random()
                attempt      = ""
                resultMsg    = ""
                inputError   = null
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar juego")
        }

        if (resultMsg.isNotEmpty()) {
            Text(resultMsg, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(Modifier.weight(1f))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al menú")
        }
    }
}