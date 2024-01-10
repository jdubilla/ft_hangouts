package com.example.fthangouts.ui.view.detailsContact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fthangouts.helper.loadImageFromInternalStorage
import com.example.fthangouts.model.User

@Composable
fun HeaderDetailsContact(contact: User) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .padding(8.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            )
        ) {
            if (contact.photo == "") {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp),
                    tint = Color.Gray
                )
            } else {
                val bitmap = loadImageFromInternalStorage(context, contact.photo)
                if (bitmap != null) {
                    val bitmapImg = bitmap.asImageBitmap()
                    Image(
                        bitmap = bitmapImg,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
        Text(
            text = contact.firstName,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}