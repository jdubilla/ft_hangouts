package com.example.fthangouts.ui.view.detailsContact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fthangouts.R
import com.example.fthangouts.model.User
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BodyDetailsContact(contact: User) {

    val context = LocalContext.current

    fun convertTimestampToDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date(timestamp)
        return sdf.format(date)
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        DetailItem(label = context.getString(R.string.first_name), value = contact.firstName)
        if (contact.lastName != "") {
            DetailItem(label = context.getString(R.string.last_name), value = contact.lastName)
        }
        DetailItem(label = context.getString(R.string.phone_number), value = contact.phoneNumber)
        if (contact.note != "") {
            DetailItem(label = "Note", value = contact.note)
        }
        if (contact.birthDate != null) {
            DetailItem(label = context.getString(R.string.birth_date), value = convertTimestampToDate(contact.birthDate))
        }
    }
}