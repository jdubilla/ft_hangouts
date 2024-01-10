package com.example.fthangouts.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileInputStream
import java.io.FileNotFoundException

fun loadImageFromInternalStorage(context: Context, fileName: String): Bitmap? {
    return try {
        val inputStream: FileInputStream = context.openFileInput(fileName)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        null
    }
}