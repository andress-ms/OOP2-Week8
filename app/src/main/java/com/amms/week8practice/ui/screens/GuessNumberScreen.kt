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

/**
 * Ejercicio 1: Adivina el número
 *
 * - Genera un número secreto aleatorio entre 1 y 10.
 * - Permite al usuario introducir un intento (solo dígitos).
 * - Valida que el número esté en el rango 1–10.
 * - Comprueba si el intento coincide con el secreto y muestra un mensaje.
 * - Permite reiniciar el juego y regresar al menú.
 */
@Composable
fun GuessNumberScreen(navController: NavHostController) {
    // Estado del número secreto (persistente mientras Compose lo recuerde).
    var secretNumber by remember { mutableStateOf((1..10).random()) }

    // Estado del texto introducido por el usuario.
    var attempt by remember { mutableStateOf("") }

    // Mensaje de resultado tras pulsar "Comprobar".
    var resultMsg by remember { mutableStateOf("") }

    // Mensaje de error en la validación de rango.
    var inputError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()      // Ocupa toda la pantalla
            .padding(16.dp),    // Padding interior
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título del ejercicio
        Text(
            text = "Adivina el número (1–10)",
            style = MaterialTheme.typography.headlineSmall
        )

        // Campo de texto para el intento
        OutlinedTextField(
            value = attempt,
            onValueChange = { new ->
                // Filtrar sólo dígitos
                val filtered = new.filter { it.isDigit() }
                attempt = filtered

                // Validar rango si hay un número
                inputError = when {
                    filtered.isEmpty() -> null
                    else -> {
                        val n = filtered.toIntOrNull()
                        if (n == null || n !in 1..10) "Ingresa un número entre 1 y 10" else null
                    }
                }
            },
            label = { Text("Tu intento") },
            isError = inputError != null,    // Marca el campo si hay error
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Texto de error, si aplica
        inputError?.let { err ->
            Text(
                text = err,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        // Botón para comprobar el intento
        Button(
            onClick = {
                val guess = attempt.toIntOrNull()
                resultMsg = when {
                    guess == null         -> "Ingresa un número válido"
                    guess !in 1..10       -> "El número debe estar entre 1 y 10"
                    guess == secretNumber -> "¡Correcto!"
                    else                  -> "Intenta de nuevo"
                }
            },
            enabled = inputError == null && attempt.isNotEmpty(), // Deshabilita si hay error
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Comprobar")
        }

        // Botón para reiniciar el juego
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

        // Mostrar el resultado (acierto/fallo)
        if (resultMsg.isNotEmpty()) {
            Text(
                text = resultMsg,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Espacio flexible para empujar el botón de abajo
        Spacer(modifier = Modifier.weight(1f))

        // Botón para volver al menú principal
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al menú")
        }
    }
}