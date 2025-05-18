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
 * Ejercicio 2: Tabla de multiplicar
 *
 * - Permite ingresar un número base.
 * - Al pulsar "Generar tabla", muestra las líneas de la tablade 1× hasta 12×.
 * - Valida que el input sea un número entero.
 * - Usa MaterialTheme y espaciados uniformes.
 */
@Composable
fun MultiplicationScreen(navController: NavHostController) {
    // Estado del texto del número base
    var baseInput by remember { mutableStateOf("") }
    // Estado de las líneas generadas (lista de Strings)
    var tableLines by remember { mutableStateOf<List<String>>(emptyList()) }
    // Mensaje de error de validación
    var errorMsg by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Tabla de multiplicar",
            style = MaterialTheme.typography.headlineSmall
        )

        // Input del número base
        OutlinedTextField(
            value = baseInput,
            onValueChange = { new ->
                baseInput = new.filter { it.isDigit() } // Solo dígitos
            },
            label = { Text("Número base") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Botón para generar la tabla
        Button(
            onClick = {
                val base = baseInput.toIntOrNull()
                if (base == null) {
                    errorMsg = "Ingresa un número válido"
                    tableLines = emptyList()
                } else {
                    errorMsg = null
                    // Genera líneas 1 a 12
                    tableLines = (1..12).map { "$base × $it = ${base * it}" }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generar tabla")
        }

        // Texto de error si aplica
        errorMsg?.let { err ->
            Text(
                text = err,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        // Muestra cada línea de la tabla
        tableLines.forEach { line ->
            Text(
                text = line,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Empuja el botón de volver al menú hacia abajo
        Spacer(modifier = Modifier.weight(1f))

        // Botón para regresar
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al menú")
        }
    }
}