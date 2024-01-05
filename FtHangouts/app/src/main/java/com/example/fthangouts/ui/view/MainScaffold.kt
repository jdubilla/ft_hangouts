package com.example.fthangouts.ui.view

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScaffold() {

    var navController = rememberNavController()

    Scaffold(
        topBar = { AppBar() },
        content = { paddingValues ->  HostController(paddingValues = paddingValues, navHostController = navController)},
        bottomBar = { BottomBar(navController = navController)}
    )
}