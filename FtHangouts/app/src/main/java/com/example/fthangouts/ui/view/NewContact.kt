package com.example.fthangouts.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fthangouts.viewModel.NewContactViewModel

@Composable
fun NewContact(vm: NewContactViewModel = viewModel()) {

    val state by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.firstName,
            onValueChange = { newValue -> vm.firstNameChanged(newValue) },
            label = { Text(text = "First Name")},
            singleLine = true
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.lastName,
            onValueChange = { newValue -> vm.lastNameChanged(newValue) },
            label = { Text(text = "Last Name")},
            singleLine = true
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Enregistrer")
        }
    }
}