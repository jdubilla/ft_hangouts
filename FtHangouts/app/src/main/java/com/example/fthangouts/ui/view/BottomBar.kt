package com.example.fthangouts.ui.view

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fthangouts.data.AppDatas

@Composable
fun BottomBar(navController: NavController) {

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
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

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