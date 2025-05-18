package com.amms.week8practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.amms.week8practice.ui.theme.AppTheme

/**
 * La actividad principal que arranca la app.
 * - Aplica el tema Material (AppTheme).
 * - Crea el NavController para controlar la navegación.
 * - Carga el NavGraph dentro del Composable content.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1️⃣ Aplicamos nuestro tema personalizado
            AppTheme {
                // 2️⃣ Creamos el controlador de navegación de Compose
                val navController = rememberNavController()
                // 3️⃣ Llamamos al grafo de navegación
                NavGraph(navController)
            }
        }
    }
}