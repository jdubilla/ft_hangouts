package com.example.fthangouts.ui.view.newContact

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.fthangouts.R

@Composable
fun PhoneNumberInput(phoneNumber: String, phoneNumberChanged: (String) -> Unit, isError: Boolean) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = phoneNumber,
        onValueChange = { newValue ->
            if (newValue.length <= 20) {
                phoneNumberChanged(newValue)
            }
        },
        label = { Text(stringResource(id = R.string.phone_number)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        isError = isError && phoneNumber.isEmpty()
    )
}