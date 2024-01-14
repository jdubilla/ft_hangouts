package com.example.fthangouts.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fthangouts.helper.DatabaseHelper

@Composable
fun TestMessagesFirst(onClick: () -> Unit, dbConnection: DatabaseHelper) {

    val allContacts = dbConnection.getAllUsers()

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = "TestMessagesFirst")
        Button(onClick = { onClick() }) {
            Text(text = "Test")
        }
        allContacts.forEach { contact ->
            ListItem(
                headlineContent = {
                    Text(text = "Headline")
                },
                overlineContent = {
                    Text(text = "Overline")
                },
                supportingContent = {
                    Text(text = "Supporting")
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )

                },
                trailingContent = {
                    Text(text = "Trailing")
                },
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}