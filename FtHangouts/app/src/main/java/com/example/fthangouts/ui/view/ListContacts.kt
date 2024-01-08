package com.example.fthangouts.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fthangouts.helper.DatabaseHelper
import java.io.FileInputStream
import java.io.FileNotFoundException

@Composable
fun ListContacts(onClick: () -> Unit, dbConnection: DatabaseHelper) {

    val allContacts = dbConnection.getAllUsers()
    val context = LocalContext.current

    fun loadImageFromInternalStorage(context: Context, fileName: String): Bitmap? {
        return try {
            val inputStream: FileInputStream = context.openFileInput(fileName)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .padding(5.dp)
            .verticalScroll(
                enabled = true,
                state = rememberScrollState()
            )
    ) {
        allContacts.forEach { contact ->
            Surface(
                onClick = { println("Ok") }
            ) {
                ListItem(
                    leadingContent = {
                        if (contact.photo == null) {
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
        Button(onClick = { onClick() }) {
            Text(text = "Cree contact")
        }
    }
}