package com.amms.week8practice.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amms.week8practice.viewmodels.MultiplicationTableViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MultiplicationTableScreen(
    navController: NavHostController,
    viewModel: MultiplicationTableViewModel = viewModel()
) {
    val base by viewModel.base.collectAsState()
    val table by viewModel.table.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tabla de multiplicar", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = base,
            onValueChange = viewModel::onBaseChange,
            label = { Text("Número base") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Button(onClick = { viewModel.generateTable() }) {
            Text("Generar tabla")
        }
        if (error.isNotEmpty()) {
            Text(error, color = MaterialTheme.colorScheme.error)
        }
        Spacer(Modifier.height(12.dp))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            table.forEach { line ->
                Text(line, style = MaterialTheme.typography.bodyMedium)
            }
        }
        Spacer(Modifier.weight(1f))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al menú")
        }
    }
}