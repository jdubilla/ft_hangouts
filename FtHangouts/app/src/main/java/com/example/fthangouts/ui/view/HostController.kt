package com.example.fthangouts.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fthangouts.data.AppDatas
import com.example.fthangouts.model.ItemNav

@Composable
fun HostController(paddingValues: PaddingValues, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ItemNav.First.name,
        modifier = Modifier.padding(paddingValues)
    ) {
        AppDatas().items.forEach { item ->
            composable(item.name) {
                when (item.name) {
                    "Contacts" -> NewContact()
                    "Messages" -> Text(text = "Messages")
                }
            }
        }
    }
}