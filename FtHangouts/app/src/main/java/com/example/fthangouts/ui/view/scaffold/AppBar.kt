package com.example.fthangouts.ui.view.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.fthangouts.model.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController, screens: Screens, currentRoute: String?) {

    var expanded by remember { mutableStateOf(false) }
    var headerColor by remember { mutableStateOf(Color(android.graphics.Color.parseColor("#664E9E"))) }

    if (currentRoute != "Permissions") {
        TopAppBar(
            title = { Text(text = if (navController.previousBackStackEntry != null) screens.title else "Contacts") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = headerColor,
                titleContentColor = MaterialTheme.colorScheme.primaryContainer
            ),
            navigationIcon = {
                if (navController.previousBackStackEntry != null) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    MyDropdownMenuItem(text = "Gray", headerColor = headerColor, color = Color.Gray, onChangeHeaderColor =  {headerColor = it})
                    MyDropdownMenuItem(text = "Black", headerColor = headerColor, color = Color.Black, onChangeHeaderColor =  {headerColor = it})
                    Divider()
                    DropdownMenuItem(
                        text = { Text("Default") },
                        onClick = {
                            headerColor = Color(0xFF664E9E)
                        },
                        trailingIcon = {
                            if (headerColor != Color.Black && headerColor != Color.Gray) {
                                Icon(
                                    Icons.Outlined.Check,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            }
        )
    }
}