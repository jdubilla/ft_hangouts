package com.example.fthangouts.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fthangouts.helper.DatabaseHelper
import com.example.fthangouts.helper.loadImageFromInternalStorage
import java.io.FileInputStream
import java.io.FileNotFoundException

@Composable
fun ListContacts(onNewContact: () -> Unit, dbConnection: DatabaseHelper, navController: NavController) {

    val allContacts = dbConnection.getAllUsers()
    val context = LocalContext.current

    Box {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
                .align(Alignment.TopStart)
        ) {
            allContacts.forEach { contact ->
                Surface(
                    onClick = {
                        println(contact.id)
                        navController.navigate(route = "DetailsContact/${contact.id}")
                    }
                ) {
                    ListItem(
                        leadingContent = {
                            if (contact.photo == "") {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(45.dp)
                                        .height(45.dp)
                                )
                            } else {
                                val bitmap = loadImageFromInternalStorage(context, contact.photo)
                                if (bitmap != null) {
                                    val bitmapImg = bitmap.asImageBitmap()
                                    Image(
                                        bitmap = bitmapImg,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(45.dp)
                                            .height(45.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        },
                        headlineContent = {
                            Text(text = "${contact.firstName} ${contact.lastName} ${contact.id}")
                        },
                        colors = ListItemDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp)),
                    )
                }
            }
        }
        ExtendedFloatingActionButton(
            onClick = { onNewContact() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "New Contact")
        }
    }
}