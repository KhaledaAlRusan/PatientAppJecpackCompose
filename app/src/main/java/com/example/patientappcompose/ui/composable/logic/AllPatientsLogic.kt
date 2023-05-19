package com.example.patientappcompose.ui.composable.logic

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import com.example.patientappcompose.ui.composable.components.PatientList
import com.example.patientappcompose.ui.composable.containers.DeletePatientDialog
import com.example.patientappcompose.ui.features.delete.DeleteViewModel
import com.example.patientappcompose.ui.features.patients.PatientsViewModel
import com.example.patientappcompose.ui.navigator.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AllPatientsLogic(
    navController: NavController,
    data: List<PatientDataModel>,
    patientViewModel: PatientsViewModel = hiltViewModel(),
    deleteViewModel: DeleteViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val deleteScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    var patientToDelete by remember { mutableStateOf<PatientDataModel?>(null) }

    val refreshScope = rememberCoroutineScope()
    val isRefreshing = patientViewModel.loadingStateFlow.collectAsState().value
    var refreshing by remember { mutableStateOf(isRefreshing) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        patientViewModel.getPatients()
        delay(1000)
        refreshing = false
    }

    val remember = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = ::refresh
    )

    Box(modifier = Modifier.pullRefresh(remember)) {
        PatientList(
            data = data,
            patientId = patientViewModel.selectedPatientId,
            onClick = { patient ->
                val id = patient.id
                patientViewModel.selectedPatientId = id
                navController.navigate(Screens.PatientDetailsScreen.route.replace("{patientId}", id))
            },
            onDelete = { patient ->
                showDialog = true
                patientToDelete = patient
            }
        )
        PullRefreshIndicator(
            refreshing,
            remember,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

    DeletePatientDialog(
        showDialog = showDialog,
        patient = patientToDelete,
        onDelete = {
            deleteScope.launch {
                deleteViewModel.deletePatient(patientToDelete!!.id)
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                showDialog = false
            }
        },
        onDismiss = { showDialog = false }
    )
}
