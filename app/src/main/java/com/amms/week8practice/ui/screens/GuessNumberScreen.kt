package com.amms.week8practice.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GuessNumberScreen(
    navController: NavHostController,
    viewModel: GuessNumberViewModel = viewModel()
) {
    val input by viewModel.input.collectAsState()
    val message by viewModel.message.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Adivina el número (1–10)", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = input,
            onValueChange = viewModel::onInputChange,
            label = { Text("Tu intento") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { viewModel.checkGuess() }) {
                Text("Comprobar")
            }
            Button(onClick = { viewModel.resetGame() }) {
                Text("Reiniciar")
            }
        }
        if (message.isNotEmpty()) {
            Text(message, style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(Modifier.weight(1f))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al menú")
        }
    }
}