package com.example.fthangouts.ui.view.conversationsList

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fthangouts.helper.loadImageFromInternalStorage
import com.example.fthangouts.model.SMSMessage
import com.example.fthangouts.model.User
import com.example.fthangouts.model.parsedDate

@Composable
fun ConversationTile(
    conversation: SMSMessage,
    contacts: List<User>,
    context: Context,
    navController: NavController
) {
    val contact: List<User> = contacts.filter { it.phoneNumber == conversation.sender }

    Surface(
        onClick = {
            navController.navigate("MessageThread/${conversation.sender}")
        }
    ) {
        ListItem(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp)),
            headlineContent = {
                Text(
                    text = if (contact.isNotEmpty()) contact.first().firstName else conversation.sender,
                    fontWeight = FontWeight.Bold
                )
            },
            supportingContent = {
                Text(
                    text = conversation.message,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            leadingContent = {
                if (contact.isNotEmpty() && contact.first().photo != "") {
                    val bitmap = loadImageFromInternalStorage(context, contact.first().photo)
                    if (bitmap != null) {
                        val bitmapImg = bitmap.asImageBitmap()
                        Image(
                            bitmap = bitmapImg,
                            contentDescription = null,
                            modifier = Modifier
                                .width(45.dp)
                                .height(45.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                        )
                    }
                } else {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )
                }
            },
            trailingContent = {
                Text(text = conversation.date.parsedDate())
            },
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}