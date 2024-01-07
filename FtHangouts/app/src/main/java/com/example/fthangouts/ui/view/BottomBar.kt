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

    NavigationBar {
        AppDatas().items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.name,
                onClick = { onClick(item.name) },
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