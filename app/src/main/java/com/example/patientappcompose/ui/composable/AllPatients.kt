package com.example.patientappcompose.ui.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import com.example.patientappcompose.ui.features.patients.PatientsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun AllPatients(
    data: List<PatientDataModel>,
    viewModel: PatientsViewModel = hiltViewModel()
) {
    val refreshScope = rememberCoroutineScope()
    val deleteScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var patientToDelete by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    fun refresh() = refreshScope.launch {
        refreshing = true
        viewModel.getPatients()
        delay(1500)
        refreshing = false
    }
    val remember = rememberPullRefreshState(refreshing = refreshing, onRefresh = ::refresh)

    Box(modifier = Modifier.pullRefresh(remember)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = data.size,
                key = { index ->
                    data[index].id
                }
            ) { index ->
                Box(modifier = Modifier.animateItemPlacement()) {
                    PatientRow(
                        record = data[index],
                        onClick = {
                            viewModel.selectedPatientId = data[index].id
                        },
                        selectedPatientId = viewModel.selectedPatientId,
                    ) {
                        showDialog = true
                        patientToDelete = it
                    }
                }

            }
        }
        PullRefreshIndicator(
            refreshing,
            remember,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
    if (showDialog) {
        DeletePatientDialog(
            onDelete = {
                deleteScope.launch {
                    viewModel.deletePatient(patientToDelete!!)
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                    showDialog = false
                }
            },
            onDismiss = { showDialog = false }
        )
    }
}
@Composable
fun DeletePatientDialog(
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

