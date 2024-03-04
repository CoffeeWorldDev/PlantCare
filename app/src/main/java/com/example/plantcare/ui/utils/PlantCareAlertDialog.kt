package com.example.plantcare.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun PlantCareAlertDialog(
    title: String,
    message: String,
    cancelText: String = "cancel",
    confirmText: String = "confirm",
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    if(isVisible) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = title)
            },
            text = {
                Text(text = message)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm()
                    }
                ) {
                    Text(confirmText)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(cancelText)
                }
            }
        )
    }
}

