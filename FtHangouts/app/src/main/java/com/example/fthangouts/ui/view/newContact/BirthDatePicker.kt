package com.example.fthangouts.ui.view.newContact

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDatePicker(datePickerState: DatePickerState) {
    DatePicker(
        state = datePickerState,
        title = { Text(text = "Birth date") },
        colors = DatePickerDefaults.colors()
    )
}