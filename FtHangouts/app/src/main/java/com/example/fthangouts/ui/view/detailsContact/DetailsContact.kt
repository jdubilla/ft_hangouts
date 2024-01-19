package com.example.fthangouts.ui.view.detailsContact

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsContact(contactId: String, dbConnection: DatabaseHelper, navController: NavController) {

    val contact: User? = dbConnection.getUserById(contactId.toInt())
    var isEditing by remember { mutableStateOf(false) }

    val callPermission = rememberPermissionState(
        permission = android.Manifest.permission.CALL_PHONE
    )

    val phoneCallLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }

    fun onClickPhoneCall() {
        if (callPermission.status.isGranted) {
            val intent = Intent(Intent.ACTION_CALL)
            if (contact != null) {
                intent.data = Uri.parse("tel:${contact.phoneNumber}")
            }
            phoneCallLauncher.launch(intent)
        } else {
            callPermission.launchPermissionRequest()
        }
    }

    if (contact == null) {
        Text(text = "Contact does not exist")
    } else {
        if (!isEditing) {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopStart)
                        .verticalScroll(
                            enabled = true,
                            state = rememberScrollState()
                        )
                ) {
                    HeaderDetailsContact(contact = contact)
                    BodyDetailsContact(contact = contact)


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ActionButtonDetailContact(
                            onClick = {
                                contact.id?.let {
                                    navController.navigate("MessageThread/${contact.phoneNumber}")
                                }
                            },
                            imageVector = Icons.Default.Email,
                            label = "SMS"
                        )
                        ActionButtonDetailContact(
                            onClick = { onClickPhoneCall() },
                            imageVector = Icons.Default.Call,
                            label = "Call"
                        )
                        ActionButtonDetailContact(
                            onClick = {
                                contact.id?.let {
                                    dbConnection.deleteUser(it)
                                    navController.navigate("ListContacts")
                                }
                            },
                            imageVector = Icons.Default.Delete,
                            label = "Delete"
                        )
                    }


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
