package com.example.fthangouts.ui.view

import android.telephony.SmsManager
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.fthangouts.helper.SmsHelper
import com.example.fthangouts.model.SMSMessage
import com.example.fthangouts.ui.view.conversationsList.SmsTextField
import kotlinx.coroutines.delay

@Composable
fun MessageThread(phoneNumber: String) {

    val context = LocalContext.current
    val manager = LocalFocusManager.current
    val smsHelper = SmsHelper()
    var allMessages by remember { mutableStateOf(emptyList<SMSMessage>()) }
    var conversationMessages by remember {
        mutableStateOf(allMessages.filter { it.sender == phoneNumber }.sortedBy { it.date }
            .reversed())
    }
    var messageText by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            val newMessagesInbox = smsHelper.getAllMessages(context)

            if (allMessages.size != newMessagesInbox.size) {
                allMessages = newMessagesInbox

                conversationMessages =
                    newMessagesInbox.filter { it.sender == phoneNumber }.sortedBy { it.date }
                        .reversed()
            }
            delay(500)
        }
    }

    fun sendMessage() {
        val smsManager = context.getSystemService(SmsManager::class.java)
        smsManager.sendTextMessage(
            phoneNumber,
            null,
            messageText,
            null,
            null
        )
        messageText = ""
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    manager.clearFocus()
                }
            }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = TextFieldDefaults.MinHeight + 7.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            reverseLayout = true
        ) {
            items(conversationMessages) { message ->
                MessageTile(message)
            }
        }
        SmsTextField(messageText,
            { messageText = it },
            { sendMessage() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}