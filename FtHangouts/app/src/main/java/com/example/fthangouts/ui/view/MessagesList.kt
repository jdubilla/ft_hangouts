package com.example.fthangouts.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.helper.SmsHelper
import com.example.fthangouts.model.SMSMessage
import com.example.fthangouts.model.parsedDate

@Composable
fun MessagesList(onClick: () -> Unit, dbConnection: DatabaseHelper) {

//    val allContacts = dbConnection.getAllUsers()
    val messagesInbox = SmsHelper().getAllMessages(LocalContext.current)
    println(messagesInbox)

    val uniqueNumbersSet = HashSet<String>()
    val uniqueMessages = mutableListOf<SMSMessage>()

    for (message in messagesInbox) {
        if (uniqueNumbersSet.add(message.sender)) {
            uniqueMessages.add(message)
        }
    }

    Column(
        modifier = Modifier
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        uniqueMessages.forEach { contact ->
            ListItem(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp)),
                headlineContent = {
                    Text(text = contact.sender)
                },
//                overlineContent = {
//                    Text(text = "Overline")
//                },
                supportingContent = {
                    Text(text = contact.message)
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )

                },
                trailingContent = {
                    Text(text = contact.date.parsedDate())
                },
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}