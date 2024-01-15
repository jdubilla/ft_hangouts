package com.example.fthangouts.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemNav(val name: String, val icon: ImageVector, val route: String) {
    object First: ItemNav("Contacts", Icons.Default.Person, "Permissions")
    object Second: ItemNav("Messages", Icons.Default.Email, "MessagesList")
}