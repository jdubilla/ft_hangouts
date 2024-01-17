package com.example.fthangouts.ui.view.conversationsList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SmsTextField(messageText: String, onValueChange: (String) -> Unit, sendMessage: () -> Unit, modifier: Modifier) {
    TextField(
        value = messageText,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        placeholder = { Text(text = "Text message") },
        trailingIcon = {
            IconButton(
                onClick = { sendMessage() },
                enabled = messageText.isNotEmpty()
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = null,
                )
            }
        }
    )
}