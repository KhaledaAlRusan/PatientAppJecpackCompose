package com.example.patientappcompose.ui.composable.containers

import androidx.compose.runtime.Composable
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import com.example.patientappcompose.ui.composable.components.DeleteDialog

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