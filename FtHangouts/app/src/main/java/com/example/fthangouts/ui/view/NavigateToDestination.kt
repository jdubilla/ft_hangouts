package com.example.fthangouts.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.ui.view.newContact.NewContact

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun NavigateToDestination(itemName: String, dbConnection: DatabaseHelper, navController: NavController) {
//    when (itemName) {
//        "Contacts" -> NewContact(dbConnection = dbConnection, navController = navController)
////        "Contacts" -> ListContacts(onClick = { /*TODO*/ }, dbConnection = )
//        "Messages" -> Text(text = "Messages")
//        // Ajoutez d'autres écrans ici si nécessaire
//        else -> Text(text = "Unknown Destination")
//    }
//}