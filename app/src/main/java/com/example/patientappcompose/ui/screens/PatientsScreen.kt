package com.example.patientappcompose.ui.screens



import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patientappcompose.ui.composable.logic.AllPatientsLogic
import com.example.patientappcompose.ui.composable.components.CircularBar
import com.example.patientappcompose.ui.composable.components.FloatButton
import com.example.patientappcompose.ui.composable.containers.ErrorMessage
import com.example.patientappcompose.ui.features.patients.PatientsViewModel
import com.example.patientappcompose.ui.navigator.Screens


@Composable
fun PatientsScreen(
    navController: NavController,
    viewModel:PatientsViewModel = hiltViewModel()
) {
    val data by viewModel.patientStateFlow.collectAsState()
    val loading by viewModel.loadingStateFlow.collectAsState()
    val error by viewModel.errorStateFlow.collectAsState()



    if (data.isNotEmpty()){
        AllPatientsLogic(navController,data = data)
    }
    if (loading){
        CircularBar()
    }
    error?.let { ErrorMessage(error = it) }

    FloatButton {
        navController.navigate(Screens.AddScreen.route)
    }
}

