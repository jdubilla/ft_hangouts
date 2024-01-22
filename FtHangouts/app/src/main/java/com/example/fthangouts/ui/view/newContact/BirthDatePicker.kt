package com.example.fthangouts.ui.view.newContact

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.fthangouts.R
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDatePicker(datePickerState: DatePickerState) {
    DatePicker(
        state = datePickerState,
        title = { Text(stringResource(id = R.string.birth_date)) },
        colors = DatePickerDefaults.colors(),
    )
}