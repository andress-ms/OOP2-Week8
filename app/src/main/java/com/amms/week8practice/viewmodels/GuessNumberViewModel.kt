package com.amms.week8practice.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GuessNumberViewModel : ViewModel() {
    private val _secret = MutableStateFlow((1..10).random())
    private val _input = MutableStateFlow("")
    private val _message = MutableStateFlow("")

    val input = _input.asStateFlow()
    val message = _message.asStateFlow()

    fun onInputChange(value: String) {
        _input.value = value
    }

    fun checkGuess() {
        val guess = _input.value.toIntOrNull()
        _message.value = when {
            guess == null             -> "Ingresa un número válido"
            guess == _secret.value    -> "¡Correcto!"
            else                       -> "Intenta de nuevo"
        }
    }

    fun resetGame() {
        _secret.value = (1..10).random()
        _input.value = ""
        _message.value = ""
    }
}
