package com.example.fthangouts.ui.view.detailsContact

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DetailItem(label: String, value: String) {
    if (value != "") {
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium

            )
            Text(
                text = value,
            )
        }
    }
}