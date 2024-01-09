package com.example.fthangouts.ui.view.newContact

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GeneralUserInfos(
    onImageSelected: (String) -> Unit,
    firstName: String,
    firstNameChanged: (String) -> Unit,
    lastName: String,
    lastNameChanged: (String) -> Unit,
    isError: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        PhotoPicker(onImageSelected = { onImageSelected(it) })
        Column {
            OutlinedTextField(
                value = firstName,
                onValueChange = { newValue ->
                    if (newValue.length <= 20) {
                        firstNameChanged(newValue)
                    }
                },
                label = { Text(text = "First Name") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                isError = isError && firstName.isEmpty()
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { newValue ->
                    if (newValue.length <= 20) {
                        lastNameChanged(newValue)
                    }
                },
                label = { Text(text = "Last Name") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
            )
        }
    }
}