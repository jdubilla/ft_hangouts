package com.example.fthangouts.ui.view.scaffold

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.model.Screens
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScaffold() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val dbConnection = DatabaseHelper(context = context)
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Screens.ConversationsList.name

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var elapsedTime by remember { mutableLongStateOf(0L) }

    LaunchedEffect(true) {
        while (true) {
            delay(1000L)
            elapsedTime++
        }
    }

    DisposableEffect(Unit) {
        val observer = object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_START -> {
                        println("PREMIER PLAN")
                        if (elapsedTime != 0L) {
                            println(elapsedTime)
                            Toast.makeText(
                                context,
                                "$elapsedTime seconds since your last visit.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    Lifecycle.Event.ON_STOP -> {
                        println("ARRIERE PLAN")
                        elapsedTime = 0L
                    }

                    else -> {}
                }
            }
        }
        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

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
            NavHostController(paddingValues, navController, dbConnection, backStackEntry)
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                currentRoute = backStackEntry?.destination?.route
            )
        }
    )
}