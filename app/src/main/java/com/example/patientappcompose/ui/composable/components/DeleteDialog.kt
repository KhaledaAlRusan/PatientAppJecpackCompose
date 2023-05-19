package com.example.patientappcompose.ui.composable.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DeleteDialog(
    onDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Are you sure you want to delete this item?") },
        confirmButton = {
            Button(onClick = onDelete) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "No")
            }
        }
    )
}