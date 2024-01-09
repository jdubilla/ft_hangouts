package com.example.fthangouts.model

import android.net.Uri

data class User(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val note: String,
    val photo: String,
    val birthDate: Long?,
    val id: Int?,
)
