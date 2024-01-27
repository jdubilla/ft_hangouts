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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.example.fthangouts.R
import com.example.fthangouts.model.User

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GeneralUserInfos(
    onImageSelected: (String) -> Unit,
    firstName: String,
    firstNameChanged: (String) -> Unit,
    lastName: String,
    lastNameChanged: (String) -> Unit,
    isError: Boolean,
    user: User?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        PhotoPicker(onImageSelected = { onImageSelected(it) }, user = user)
        Column {
            OutlinedTextField(
                value = firstName,
                onValueChange = { newValue ->
                    if (newValue.length <= 20) {
                        firstNameChanged(newValue)
                    }
                },
                label = { Text(stringResource(id = R.string.first_name)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                isError = isError && (firstName.isEmpty() || firstName.isBlank())
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { newValue ->
                    if (newValue.length <= 20) {
                        lastNameChanged(newValue)
                    }
                },
                label = { Text(stringResource(id = R.string.last_name)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
            )
        }
    }
}