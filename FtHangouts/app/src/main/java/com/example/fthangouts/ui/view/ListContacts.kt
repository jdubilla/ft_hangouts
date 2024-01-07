package com.example.fthangouts.ui.view

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.fthangouts.helper.DatabaseHelper

@Composable
fun ListContacts(dbConnection: DatabaseHelper) {

    val allContacts = dbConnection.getAllUsers()

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .padding(5.dp)
            .verticalScroll(
                enabled = true,
                state = rememberScrollState()
            )
    ) {
        allContacts.forEach { contact ->
            Surface(
                onClick = { println("Ok") }
            ) {
                ListItem(
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                        )

                    },
                    headlineContent = {
                        Text(text = "${contact.firstName} ${contact.lastName} ${contact.id}")
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                            modifier = Modifier
                        .clip(RoundedCornerShape(15.dp)),
//                    shadowElevation = 70.dp,
//                tonalElevation = 90.dp
                )
            }
        }
    }
}