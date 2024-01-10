package com.example.fthangouts.ui.view.newContact

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.model.User
import com.example.fthangouts.viewModel.NewContactViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContact(
    dbConnection: DatabaseHelper,
    vm: NewContactViewModel = viewModel(),
    navController: NavController,
    user: User? = null
) {

    val state by vm.uiState.collectAsState()
    var datePickerState = rememberDatePickerState()
    val manager = LocalFocusManager.current
    var firstLoad by remember { mutableStateOf(true) }


    fun createContactError(): Boolean {
        return state.firstName.isEmpty() || state.phoneNumber.isEmpty()
    }

    if (user != null && firstLoad) {
        vm.firstNameChanged(user.firstName)
        vm.lastNameChanged(user.lastName)
        vm.phoneNumberChanged(user.phoneNumber)
        vm.noteChanged(user.note)
        vm.imageChanged(user.photo)

        if (user.birthDate?.toInt() != 0) {
            println(user)
            datePickerState = rememberDatePickerState(user.birthDate)
        }
        firstLoad = false
    }

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
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        GeneralUserInfos(
            onImageSelected = { vm.imageChanged(it) },
            firstName = state.firstName,
            firstNameChanged = { vm.firstNameChanged(it) },
            lastName = state.lastName,
            lastNameChanged = { vm.lastNameChanged(it) },
            isError = state.isError,
            user = user
        )
        PhoneNumberInput(
            phoneNumber = state.phoneNumber,
            phoneNumberChanged = { vm.phoneNumberChanged(it) },
            isError = state.isError
        )
        NoteInput(note = state.note, noteChanged = { vm.noteChanged(it) }, isError = state.isError)
        BirthDatePicker(datePickerState = datePickerState)
        Button(onClick = {
            if (!createContactError()) {
                val newUser = User(
                    state.firstName,
                    state.lastName,
                    state.phoneNumber,
                    state.note,
                    state.image,
                    datePickerState.selectedDateMillis,
                    if (user == null) 0 else user.id
                )
                if (user == null) {
                    dbConnection.addUser(newUser)
                } else {
                    dbConnection.updateUser(newUser)
                }
                navController.navigateUp()
            } else {
                vm.isErrorChanged(true)
            }
        }) {
            Text(text = if (user == null) "Create Contact" else "Edit contact")
        }
    }
}
