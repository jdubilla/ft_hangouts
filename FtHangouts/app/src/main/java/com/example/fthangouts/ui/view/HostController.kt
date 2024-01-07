package com.example.fthangouts.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fthangouts.data.AppDatas
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.model.ItemNav

@Composable
fun HostController(paddingValues: PaddingValues, navHostController: NavHostController) {

    val context = LocalContext.current
    val dbConnection = DatabaseHelper(context = context)

    NavHost(
        navController = navHostController,
        startDestination = ItemNav.Second.name,
        modifier = Modifier.padding(paddingValues)
    ) {
        AppDatas().items.forEach { item ->
            composable(item.name) {
                NavigateToDestination(itemName = item.name, dbConnection = dbConnection)
            }
        }
    }
}