package com.example.fthangouts.model

data class Conversation(
    val address: String,
    val body: String,
    val creator: String,
    val date: Long,
    val locked: Boolean,
    val read: Boolean,
    val seen: Boolean,
    val subject: String,
)
