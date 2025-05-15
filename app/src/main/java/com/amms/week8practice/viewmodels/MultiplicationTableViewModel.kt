package com.amms.week8practice.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MultiplicationTableViewModel : ViewModel() {
    private val _base = MutableStateFlow("")
    private val _table = MutableStateFlow<List<String>>(emptyList())
    private val _error = MutableStateFlow("")

    val base = _base.asStateFlow()
    val table = _table.asStateFlow()
    val error = _error.asStateFlow()

    fun onBaseChange(value: String) {
        _base.value = value
    }

    fun generateTable() {
        val n = _base.value.toIntOrNull()
        if (n == null) {
            _error.value = "Ingresa un número válido"
            _table.value = emptyList()
        } else {
            _error.value = ""
            _table.value = (1..12).map { "${n} × ${it} = ${n * it}" }
        }
    }
}