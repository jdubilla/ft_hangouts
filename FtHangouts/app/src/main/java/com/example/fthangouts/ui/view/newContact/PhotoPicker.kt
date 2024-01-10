package com.example.fthangouts.ui.view.newContact

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.fthangouts.helper.loadImageFromInternalStorage
import com.example.fthangouts.model.User
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PhotoPicker(onImageSelected: (String) -> Unit, user: User?) {
    val context = LocalContext.current
    val contract = ActivityResultContracts.GetContent()
    val type = "image/*"
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var isReplaced by remember { mutableStateOf(false) }

    fun saveImageToInternalStorage(context: Context, uri: Uri, imageName: String) {
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = context.openFileOutput(imageName, Context.MODE_PRIVATE)
        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
    }

    fun bitmapToUri(context: Context, bitmap: Bitmap): Uri? {
        val imagesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val date = LocalDateTime.now().toString().replace(Regex("[:.-]"), "")
        val imageFile = File(imagesDir, "$date.jpg")

        try {
            val outputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return Uri.fromFile(imageFile)
    }

    if (user != null && user.photo != "" && !isReplaced) {
        val bitmapImg = loadImageFromInternalStorage(context, user.photo)
        if (bitmapImg != null) {
            val loadedUri = bitmapToUri(context, bitmapImg)
            if (loadedUri != null) {
                imageUri = loadedUri
                println("Ok")
                isReplaced = true
            }
        }
    }

    val imageLauncher = rememberLauncherForActivityResult(
        contract = contract,
        onResult = { uri ->
            if (uri != null) {
                val date = LocalDateTime.now().toString().replace(Regex("[:.-]"), "")
                val nameImage = "$date.jpg"
                saveImageToInternalStorage(context, uri, nameImage)
                onImageSelected(nameImage)
                imageUri = uri
            } else {
                imageUri = null
            }
        }
    )

    Surface(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp),
        shadowElevation = 8.dp,
        shape = CircleShape,
        onClick = {
            imageLauncher.launch(type)
        }
    ) {
        if (imageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(imageUri)
//                        .size(50, 50)
                        .build()
                ),
                contentDescription = imageUri.toString(),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}