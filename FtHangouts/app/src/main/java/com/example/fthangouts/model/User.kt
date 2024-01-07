package com.example.fthangouts.model

data class User(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val note: String,
    val birthDate: Long?,
    val id: Int?,
    )
