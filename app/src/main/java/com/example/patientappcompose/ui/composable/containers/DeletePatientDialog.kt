package com.example.patientappcompose.ui.composable

import androidx.compose.runtime.Composable
import com.example.patientappcompose.domain.model.patients.PatientDataModel

@Composable
fun DeletePatientDialog(
    showDialog: Boolean,
    patient: PatientDataModel?,
    onDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog && patient != null) {
        DeleteDialog(
            onDelete = onDelete,
            onDismiss = onDismiss
        )
    }
}