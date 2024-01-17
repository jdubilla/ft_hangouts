package com.example.fthangouts.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.model.ItemNav
import com.example.fthangouts.model.Screens
import com.example.fthangouts.ui.view.conversationsList.ConversationsList
import com.example.fthangouts.ui.view.detailsContact.DetailsContact
import com.example.fthangouts.ui.view.newContact.NewContact

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScaffold() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val dbConnection = DatabaseHelper(context = context)
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Screens.ConversationsList.name

    fun extractScreenName(route: String): String {
        val indexOfFirstBrace = route.indexOf("{")
        return if (indexOfFirstBrace != -1) {
            route.substring(0, indexOfFirstBrace - 1)
        } else {
            route
        }
    }

    val currentScreenName = extractScreenName(currentRoute)
    val currentScreen = Screens.valueOf(currentScreenName)

    Scaffold(
        topBar = {
            AppBar(
                navController = navController,
                screens = currentScreen,
                currentRoute = backStackEntry?.destination?.route
            )
        },
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = ItemNav.Second.route,
                modifier = Modifier.padding(paddingValues)
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
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                currentRoute = backStackEntry?.destination?.route
            )
        }
    )
}