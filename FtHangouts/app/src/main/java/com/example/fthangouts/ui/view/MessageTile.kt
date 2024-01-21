package com.example.fthangouts.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fthangouts.model.SMSMessage

@Composable
fun MessageTile(message: SMSMessage) {

    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp * 0.55)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = if (message.type == 1) Arrangement.Start else Arrangement.End
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = screenWidth.dp)
                .padding(
                    start = if (message.type == 1) 10.dp else 0.dp,
                    end = if (message.type != 1) 10.dp else 0.dp,
                    top = 10.dp
                )
                .clip(RoundedCornerShape(10.dp))
                .background(if (message.type == 1) Color.LightGray else MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                text = message.message,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}