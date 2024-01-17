package com.example.fthangouts.ui.view

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.fthangouts.data.AppDatas

@Composable
fun BottomBar(navController: NavController, currentRoute: String?) {

    fun onClick(route: String) {
        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let { s ->
                popUpTo(s) { saveState = true }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun itemSelected(item: String): Boolean {
        return when (item) {
            "Contacts" -> currentRoute in listOf("ListContacts", "NewContact") ||
                    currentRoute?.startsWith("DetailsContact/") == true
            "Messages" -> currentRoute == "ConversationsList"
            else -> false
        }
    }

    if (currentRoute != null) {
        println(currentRoute.startsWith("MessageThread/"))
    }

    if (currentRoute != "Permissions" && (currentRoute != null && !currentRoute.startsWith("MessageThread/"))) {
        NavigationBar {
            AppDatas().items.forEach { item ->
                NavigationBarItem(
                    selected = itemSelected(item.name),
                    onClick = { onClick(if (item.route == "Permissions") "ListContacts" else item.route) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                    },
                    label = { Text(text = item.name) }
                )
            }
        }
    }
}