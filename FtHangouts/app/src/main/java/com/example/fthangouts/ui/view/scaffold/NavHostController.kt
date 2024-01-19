package com.example.fthangouts.ui.view.scaffold

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.model.ItemNav
import com.example.fthangouts.ui.view.ListContacts
import com.example.fthangouts.ui.view.MessageThread
import com.example.fthangouts.ui.view.Permissions
import com.example.fthangouts.ui.view.conversationsList.ConversationsList
import com.example.fthangouts.ui.view.detailsContact.DetailsContact
import com.example.fthangouts.ui.view.newContact.NewContact

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostController(
    paddingValues: PaddingValues,
    navController: NavHostController,
    dbConnection: DatabaseHelper,
    backStackEntry: NavBackStackEntry?
) {
    NavHost(
        navController = navController,
        startDestination = ItemNav.First.route,
        modifier = androidx.compose.ui.Modifier.padding(paddingValues)
    ) {
        composable("Permissions") {
            Permissions(
                onPermissionGranted = {
                    navController.popBackStack()
                    navController.navigate(route = "ListContacts")
                }
            )
        }
        composable("ConversationsList") {
            ConversationsList(
                navController = navController,
                dbConnection = dbConnection
            )
        }
        composable("MessageThread/{phoneNumber}") {
            val phoneNumber = backStackEntry?.arguments?.getString("phoneNumber")
            phoneNumber?.let { phone ->
                MessageThread(phone)
            }
        }
        composable("ListContacts") {
            ListContacts(
                onNewContact = {
                    navController.navigate(route = "NewContact")
                },
                navController = navController,
                dbConnection = dbConnection,
            )
        }
        composable("NewContact") {
            NewContact(dbConnection = dbConnection, navController = navController)
        }
        composable("DetailsContact/{contactId}") { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")
            contactId?.let { id ->
                DetailsContact(
                    contactId = id,
                    dbConnection = dbConnection,
                    navController = navController
                )
            }
        }
    }
}