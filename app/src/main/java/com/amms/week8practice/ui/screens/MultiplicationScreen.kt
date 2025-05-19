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
import java.text.DecimalFormat

/**
 * Ejercicio 2 modificado: Tabla de multiplicar acepta negativos y decimales.
 */
@Composable
fun MultiplicationScreen(navController: NavHostController) {
    // Estado del texto del número base
    var baseInput by remember { mutableStateOf("") }
    // Estado de las líneas de salida
    var tableLines by remember { mutableStateOf<List<String>>(emptyList()) }
    // Mensaje de error de validación
    var errorMsg by remember { mutableStateOf<String?>(null) }
    // Para formatear con 2 decimales
    val fmt = remember { DecimalFormat("#.##") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tabla de multiplicar",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = baseInput,
            onValueChange = { new ->
                // Permitimos cualquier carácter; validamos al generar la tabla
                baseInput = new
            },
            label = { Text("Número base (p. ej. -2.5)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // Intentamos parsear a Double (soporta negativos y decimales)
                val base = baseInput.toDoubleOrNull()
                if (base == null) {
                    errorMsg = "Ingresa un número válido (p. ej. -3.14)"
                    tableLines = emptyList()
                } else {
                    errorMsg = null
                    // Generamos la tabla del 1 al 12 formateando resultados
                    tableLines = (1..12).map { i ->
                        val product = base * i
                        "${fmt.format(base)} × $i = ${fmt.format(product)}"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generar tabla")
        }

        // Mostrar error si existe
        errorMsg?.let { err ->
            Text(
                text = err,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        // Mostrar cada línea de la tabla
        tableLines.forEach { line ->
            Text(
                text = line,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al menú")
        }
    }
}