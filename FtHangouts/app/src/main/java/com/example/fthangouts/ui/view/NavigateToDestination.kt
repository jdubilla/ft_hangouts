package com.example.fthangouts.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.ui.view.newContact.NewContact

@Composable
fun NavigateToDestination(itemName: String, dbConnection: DatabaseHelper) {
    when (itemName) {
        "Contacts" -> NewContact(dbConnection = dbConnection)
        "Messages" -> Text(text = "Messages")
        // Ajoutez d'autres écrans ici si nécessaire
        else -> Text(text = "Unknown Destination")
    }
}