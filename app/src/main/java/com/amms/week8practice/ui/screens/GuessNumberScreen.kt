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
 * - Comprueba si el intento coincide con el secreto y muestra:
 *     • “¡Correcto!” (y la cantidad de intentos)
 *     • “Número secreto es mayor” o “Número secreto es menor”
 * - Lleva un contador de intentos y lo muestra en pantalla.
 * - Al fallar 3 veces, reinicia el juego (nuevo número + contador a 0).
 * - Botón para reiniciar manualmente y para volver al menú.
 */
@Composable
fun GuessNumberScreen(navController: NavHostController) {
    // 1️⃣ Estado del número secreto (entre 1 y 10)
    var secretNumber by remember { mutableStateOf((1..10).random()) }
    // 2️⃣ Estado del texto ingresado por el usuario
    var attempt by remember { mutableStateOf("") }
    // 3️⃣ Mensaje de resultado (“Correcto”, “mayor”, “menor”)
    var resultMsg by remember { mutableStateOf("") }
    // 4️⃣ Mensaje de error de validación de rango
    var inputError by remember { mutableStateOf<String?>(null) }
    // 5️⃣ Contador de intentos realizados
    var attemptsCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()      // Ocupa toda la pantalla
            .padding(16.dp),    // Espaciado interior
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Adivina el número (1–10)",
            style = MaterialTheme.typography.headlineSmall
        )

        // Mostrar contador de intentos
        Text(
            text = "Intentos: $attemptsCount",
            style = MaterialTheme.typography.bodyLarge
        )

        // Campo de texto para el intento
        OutlinedTextField(
            value = attempt,
            onValueChange = { new ->
                // Filtrar solo dígitos
                val filtered = new.filter { it.isDigit() }
                attempt = filtered

                // Validar que, si hay texto, esté entre 1 y 10
                inputError = when {
                    filtered.isEmpty() -> null
                    else -> {
                        val n = filtered.toIntOrNull()
                        if (n == null || n !in 1..10) {
                            "Ingresa un número entre 1 y 10"
                        } else null
                    }
                }
            },
            label = { Text("Tu intento") },
            isError = inputError != null,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Mensaje de error de validación
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
                // Intento como Int
                val guess = attempt.toIntOrNull()
                // Solo procesar si es válido
                if (guess != null && guess in 1..10) {
                    attemptsCount++  // Incrementar contador

                    resultMsg = when {
                        guess == secretNumber -> {
                            "¡Correcto! Lo lograste en $attemptsCount intentos."
                        }
                        guess < secretNumber -> {
                            "Número secreto es mayor que $guess."
                        }
                        else -> {
                            "Número secreto es menor que $guess."
                        }
                    }

                    // Si agotó 3 intentos sin acertar, reiniciar automáticamente
                    if (guess != secretNumber && attemptsCount >= 3) {
                        // Guardar mensaje de reinicio y nuevo número
                        resultMsg += "\nSe han agotado 3 intentos. Juego reiniciado."
                        secretNumber = (1..10).random()
                        attemptsCount = 0
                    }
                } else {
                    // No debería ocurrir si el botón está deshabilitado, pero por si acaso:
                    resultMsg = "Ingresa un número válido entre 1 y 10."
                }
                // Limpiar el campo de texto para el siguiente intento
                attempt = ""
            },
            // Solo habilitar si no hay error y el campo no está vacío
            enabled = inputError == null && attempt.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Comprobar")
        }

        // Botón para reiniciar manualmente
        Button(
            onClick = {
                secretNumber = (1..10).random()
                attempt = ""
                resultMsg = ""
                inputError = null
                attemptsCount = 0
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar juego")
        }

        // Mostrar mensaje de resultado (acierto, pista o reinicio automático)
        if (resultMsg.isNotEmpty()) {
            Text(
                text = resultMsg,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón para volver al menú principal
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al menú")
        }
    }
}