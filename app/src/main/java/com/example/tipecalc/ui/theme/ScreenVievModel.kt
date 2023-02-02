package com.example.tipecalc.ui.theme

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat

class ScreenVieWModel : ViewModel() {

    private val _amountInput = MutableStateFlow(String())
    val amountInput: StateFlow<String> = _amountInput.asStateFlow()

    private val _tipPercentInput = MutableStateFlow(String())
    val tipPercentInput: StateFlow<String> = _tipPercentInput.asStateFlow()

    fun setAmount(amount: String) {
        _amountInput.value = amount
    }

    fun setTipPercent(tipPercent: String) {
        _tipPercentInput.value = tipPercent
    }

    fun calculateTip(): String {
        val amount = amountInput.value.toDoubleOrNull() ?: 0.0
        val tipPercent = tipPercentInput.value.toDoubleOrNull() ?: 0.0
        val tip = tipPercent / 100 * amount
        return NumberFormat.getCurrencyInstance().format(tip)
    }

}