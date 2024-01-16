package com.example.fthangouts.ui.view.ConversationsList


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.helper.SmsHelper
import com.example.fthangouts.model.SMSMessage
import kotlinx.coroutines.delay

@Composable
fun ConversationsList(onClick: () -> Unit, dbConnection: DatabaseHelper) {
    val context = LocalContext.current
    val smsHelper = SmsHelper()
    val contacts = DatabaseHelper(context).getAllUsers()
    var messagesInbox by remember { mutableStateOf(emptyList<SMSMessage>()) }

    var uniqueMessages by remember { mutableStateOf(messagesInbox.distinctBy { it.sender }) }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            val newMessagesInbox = smsHelper.getAllMessages(context)

            if (messagesInbox.size != newMessagesInbox.size) {
                println("Okkkk")
                messagesInbox = newMessagesInbox

                uniqueMessages = newMessagesInbox.distinctBy { it.sender }
            }
            delay(1000)
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(uniqueMessages) { conversation ->
            ConversationTile(conversation, contacts, context)
        }
    }
}