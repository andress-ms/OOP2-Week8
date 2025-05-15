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
    // Estado: número secreto, intento del usuario y mensaje de resultado
    var secretNumber by remember { mutableStateOf((1..10).random()) }
    var attempt      by remember { mutableStateOf("") }
    var resultMsg    by remember { mutableStateOf("") }

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
            onValueChange = { attempt = it },
            label = { Text("Tu intento") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val guess = attempt.toIntOrNull()
                resultMsg = when {
                    guess == null            -> "Ingresa un número válido"
                    guess == secretNumber    -> "¡Correcto!"
                    else                     -> "Intenta de nuevo"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Comprobar")
        }

        Button(
            onClick = {
                secretNumber = (1..10).random()
                attempt      = ""
                resultMsg    = ""
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