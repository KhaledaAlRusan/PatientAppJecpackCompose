package com.example.patientappcompose.ui.screens


import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patientappcompose.domain.model.add.AddPatientModel
import com.example.patientappcompose.ui.composable.components.CircularBar
import com.example.patientappcompose.ui.composable.containers.AddPatientContainer
import com.example.patientappcompose.ui.composable.containers.ErrorMessage
import com.example.patientappcompose.ui.features.add.AddPatientViewModel
import com.example.patientappcompose.ui.navigator.Screens

@Composable
fun AddPatientScreen(
    navController : NavController? = null
) {
    val viewModel: AddPatientViewModel = hiltViewModel()
    val success = viewModel.addPatientsStateFlow.collectAsState().value
    val context = LocalContext.current
    val loading = viewModel.loadingStateFlow.collectAsState().value
    val error = viewModel.errorStateFlow.collectAsState().value

    AddPatientContainer {
        viewModel.addPatient(
            AddPatientModel(
                it[0],it[1],it[2],it[3],it[4],it[5],
            )
        )
    }
    LaunchedEffect(success) {
        if (success != null) {
            Toast.makeText(context, success.name, Toast.LENGTH_SHORT).show()
            navController?.popBackStack(Screens.MainScreen.route, false)
        }
    }
    if (loading){
        CircularBar()
    }
    if (error != null){
        ErrorMessage(error = error)
    }
}

