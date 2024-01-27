package com.example.fthangouts.ui.view.conversationsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fthangouts.R
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.helper.SmsHelper
import com.example.fthangouts.model.SMSMessage
import com.example.fthangouts.model.User
import kotlinx.coroutines.delay
import java.sql.Connection

@Composable
fun ConversationsList(navController: NavController, dbConnection: DatabaseHelper) {
    val context = LocalContext.current
    val smsHelper = SmsHelper()
    val contacts = DatabaseHelper(context).getAllUsers()
    var allMessages by remember { mutableStateOf(emptyList<SMSMessage>()) }

    var uniqueMessages by remember { mutableStateOf(allMessages.distinctBy { it.sender }) }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            val newMessagesInbox = smsHelper.getAllMessages(context).sortedByDescending { it.date }

            if (allMessages.size != newMessagesInbox.size) {
                for (message in newMessagesInbox) {
                    if (!dbConnection.phoneNumberIsRegister(message.sender)) {
                        dbConnection.addUser(
                            User(
                                firstName = message.sender,
                                lastName = "",
                                phoneNumber = message.sender,
                                note = "",
                                photo = "",
                                birthDate = null,
                                id = null
                            )
                        )
                    }
                }
                allMessages = newMessagesInbox
                uniqueMessages = newMessagesInbox.distinctBy { it.sender }
            }
            delay(1000)
        }
    }

    if (uniqueMessages.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                stringResource(id = R.string.no_conversations),
                color = Color.LightGray,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(uniqueMessages) { conversation ->
                ConversationTile(conversation, contacts, context, navController)
            }
        }
    }
}