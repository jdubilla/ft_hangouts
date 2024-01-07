package com.example.fthangouts.ui.view

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.model.User
import com.example.fthangouts.viewModel.NewContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContact(dbConnection: DatabaseHelper, vm: NewContactViewModel = viewModel()) {

    val state by vm.uiState.collectAsState()
    val datePickerState = rememberDatePickerState()
    val manager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    manager.clearFocus()
                }
            }
            .verticalScroll(
                enabled = true,
                state = rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.firstName,
            onValueChange = { newValue ->
                if (newValue.length <= 20) {
                    vm.firstNameChanged(newValue)
                }
            },
            label = { Text(text = "First Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.lastName,
            onValueChange = { newValue ->
                if (newValue.length <= 20) {
                    vm.lastNameChanged(newValue)
                }
            },
            label = { Text(text = "Last Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.phoneNumber,
            onValueChange = { newValue ->
                if (newValue.length <= 20) {
                    vm.phoneNumberChanged(newValue)
                }
            },
            label = { Text(text = "Phone number") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.note,
            onValueChange = { newValue ->
                if (newValue.length <= 200) {
                    vm.noteChanged(newValue)
                }
            },
            label = { Text(text = "Note") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
        )
        DatePicker(
            state = datePickerState,
            title = { Text(text = "Birth date") },
            colors = DatePickerDefaults.colors()
        )
        Button(onClick = { println(datePickerState.selectedDateMillis) }) {
            Text(text = "Test Date")
        }
        Button(onClick = {
            val user = User(
                state.firstName,
                state.lastName,
                state.phoneNumber,
                state.note,
                datePickerState.selectedDateMillis,
                0
            )
            dbConnection.addUser(user)
        }) {
            Text(text = "Enregistrer")
        }
        Button(onClick = {
            println(dbConnection.getAllUsers())
        }) {
            Text(text = "Recuperer les contacts")
        }
    }
}