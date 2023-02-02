package com.example.tipecalc.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import java.text.NumberFormat

class ScreenVieWModel : ViewModel() {

    private val _amountInput = MutableStateFlow(String())
    val amountInput: StateFlow<String> = _amountInput.asStateFlow()

    private val _tipPercentInput = MutableStateFlow(String())
    val tipPercentInput: StateFlow<String> = _tipPercentInput.asStateFlow()

    private val _roundUp = MutableStateFlow(false)
    val roundUp: StateFlow<Boolean> = _roundUp.asStateFlow()

    val tip = combine(_amountInput, _tipPercentInput ,_roundUp) { amount, tipPercent, roundUp ->
        var tip = tipPercent.toDouble() / 100 * amount.toDouble()
        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }
        NumberFormat.getCurrencyInstance().format(tip)
    }.stateIn(viewModelScope, SharingStarted.Lazily, "0.00")

    fun setAmount(amount: String) {
        _amountInput.value = amount
    }

    fun setTipPercent(tipPercent: String) {
        _tipPercentInput.value = tipPercent
    }

    fun setRoundUp(roundUp: Boolean) {
        _roundUp.value = roundUp
    }
    private fun String?.toDouble() = this?.toDoubleOrNull() ?: 0.0

}