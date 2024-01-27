package com.example.fthangouts.viewModel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.fthangouts.ui.uiState.NewContactState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewContactViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NewContactState())

    var uiState: StateFlow<NewContactState> = _uiState.asStateFlow()

    fun imageChanged(newValue: String) {
        _uiState.update { it.copy(image = newValue) }
    }

    fun firstNameChanged(newValue: String) {
        _uiState.update { it.copy(firstName = newValue) }
    }

    fun lastNameChanged(newValue: String) {
        _uiState.update { it.copy(lastName = newValue) }
    }

    fun phoneNumberChanged(newValue: String) {
        val regex = Regex("^[0-9]+$")
        var phoneNumber = newValue

        if (newValue.isNotEmpty() && newValue[0] == '+') {
            phoneNumber = newValue.substring(1)

            if (newValue.count() == 1) {
                _uiState.update { it.copy(phoneNumber = newValue) }
            }
        }

        if (phoneNumber.matches(regex) || newValue.isEmpty()) {
            _uiState.update { it.copy(phoneNumber = newValue) }
        }
    }

    fun noteChanged(newValue: String) {
        _uiState.update { it.copy(note = newValue) }
    }

    fun isErrorChanged(newValue: Boolean) {
        _uiState.update { it.copy(isError = newValue) }
    }
}