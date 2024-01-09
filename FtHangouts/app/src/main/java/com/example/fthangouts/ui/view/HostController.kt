package com.example.fthangouts.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
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

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun HostController(paddingValues: PaddingValues, navHostController: NavHostController) {
//
//    val context = LocalContext.current
//    val dbConnection = DatabaseHelper(context = context)
//
//    NavHost(
//        navController = navHostController,
//        startDestination = ItemNav.Second.route,
//        modifier = Modifier.padding(paddingValues)
//    ) {
//        AppDatas().items.forEach { item ->
//            composable(item.route) {
//                NavigateToDestination(
//                    itemName = item.route,
//                    dbConnection = dbConnection,
//                    navController = navHostController
//                )
//            }
//        }
//    }
//}