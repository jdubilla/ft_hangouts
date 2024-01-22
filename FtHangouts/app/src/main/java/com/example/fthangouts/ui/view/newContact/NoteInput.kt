package com.example.fthangouts.ui.view.newContact

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun NoteInput(note: String, noteChanged: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = note,
        onValueChange = { newValue ->
            if (newValue.length <= 200) {
                noteChanged(newValue)
            }
        },
        label = { Text(text = "Note") },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
    )
}