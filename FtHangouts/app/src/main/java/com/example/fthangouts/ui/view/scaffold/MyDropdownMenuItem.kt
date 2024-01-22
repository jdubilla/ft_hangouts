package com.example.fthangouts.ui.view.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun MyDropdownMenuItem(text: String, headerColor: Color, color: Color, onChangeHeaderColor: (Color) -> Unit) {
    DropdownMenuItem(
        text = { Text(text) },
        onClick = {
                  onChangeHeaderColor(color)
        },
        trailingIcon = {
            if (headerColor == color) {
                Icon(
                    Icons.Outlined.Check,
                    contentDescription = null
                )
            }
        }
    )
}