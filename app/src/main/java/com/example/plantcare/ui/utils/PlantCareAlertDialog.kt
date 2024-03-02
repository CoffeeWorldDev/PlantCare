package com.example.plantcare.ui.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PlantCareAlertDialog(
    title: String,
    message: String,
    cancelText: String,
    confirmText: String,
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
                Button(
                    onClick = {
                        onConfirm()
                    }
                ) {
                    Text(text = confirmText)
                }
            },
            dismissButton = {
                Button(onClick = {
                    onDismiss()
                }
                ) {
                    Text(text = cancelText)
                }
            }
        )
    }
}