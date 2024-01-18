package com.example.fthangouts.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permissions(onPermissionGranted: () -> Unit) {
    val readSmsPermission = rememberPermissionState(
        permission = android.Manifest.permission.READ_SMS
    )
    val sendSmsPermission = rememberPermissionState(
        permission = android.Manifest.permission.SEND_SMS
    )
//    val callPermission = rememberPermissionState(
//        permission = android.Manifest.permission.CALL_PHONE
//    )

    if (readSmsPermission.status.isGranted && sendSmsPermission.status.isGranted) {
        onPermissionGranted()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                if (readSmsPermission.status.isGranted) {
                    sendSmsPermission.launchPermissionRequest()
//                    callPermission.launchPermissionRequest()
                } else {
                    readSmsPermission.launchPermissionRequest()
//                    callPermission.launchPermissionRequest()
                }
            }) {
                val textToShow = if (readSmsPermission.status.isGranted) {
                    "Go to app"
                } else {
                    "SMS permissions"
                }
                Text(textToShow)
            }
        }
    }
}