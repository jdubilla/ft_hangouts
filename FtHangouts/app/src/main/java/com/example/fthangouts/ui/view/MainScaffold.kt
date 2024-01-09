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
import com.example.fthangouts.ui.view.newContact.NewContact

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScaffold() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val dbConnection = DatabaseHelper(context = context)
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        Screens.valueOf(backStackEntry?.destination?.route ?: Screens.FirstMessage.name)

    Scaffold(
        topBar = {
            AppBar(navController = navController, screens = currentScreen)
        },
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = ItemNav.First.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("FirstMessage") {
                    TestMessagesFirst(
                        onClick = {
                            navController.navigate(route = "SecondMessage")
                        },
                        dbConnection = dbConnection
                    )
                }
                composable("SecondMessage") {
                    TestMessagesSecond()
                }
                composable("ListContacts") {
                    ListContacts(
                        onNewContact = {
                            navController.navigate(route = "NewContact")
                        },
                        dbConnection = dbConnection,

                    )
                }
                composable("NewContact") {
                    NewContact(dbConnection = dbConnection, navController = navController)
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