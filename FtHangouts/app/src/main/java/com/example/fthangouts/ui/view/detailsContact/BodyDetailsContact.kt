package com.example.fthangouts.ui.view.detailsContact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fthangouts.model.User
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BodyDetailsContact(contact: User) {

    println(contact.birthDate)

    fun convertTimestampToDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date(timestamp)
        return sdf.format(date)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        DetailItem(label = "First name", value = contact.firstName)
        DetailItem(label = "Last name", value = contact.lastName)
        DetailItem(label = "Phone number", value = contact.phoneNumber)
        DetailItem(label = "Note", value = contact.note)
        if (contact.birthDate != null) {
            DetailItem(label = "Birth date", value = convertTimestampToDate(contact.birthDate))
        }
    }
}