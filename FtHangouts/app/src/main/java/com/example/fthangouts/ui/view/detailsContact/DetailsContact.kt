package com.example.fthangouts.ui.view.detailsContact

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.model.User
import com.example.fthangouts.ui.view.newContact.NewContact

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsContact(contactId: String, dbConnection: DatabaseHelper, navController: NavController) {

    val contact: User? = dbConnection.getUserById(contactId.toInt())
    var isEditing by remember { mutableStateOf(false) }

    if (contact == null) {
        Text(text = "Contact does not exist")
    } else {
        if (!isEditing) {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopStart),
                ) {
                    HeaderDetailsContact(contact = contact)
                    BodyDetailsContact(contact = contact)
                }
                FloatingActionButton(
                    onClick = { isEditing = true },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(15.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                }
            }
        } else {
            NewContact(dbConnection = dbConnection, navController = navController, user = contact)
        }
    }
}