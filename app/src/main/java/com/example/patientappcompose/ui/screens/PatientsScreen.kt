package com.example.patientappcompose.ui.screens



import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patientappcompose.ui.composable.AllPatientsLogic
import com.example.patientappcompose.ui.composable.CircularBar
import com.example.patientappcompose.ui.composable.FloatButton
import com.example.patientappcompose.ui.features.patients.PatientsViewModel
import com.example.patientappcompose.ui.navigator.Screens


@Composable
fun PatientsScreen(
    navController: NavController,
    viewModel:PatientsViewModel = hiltViewModel()
) {
    val data = viewModel.patientStateFlow.collectAsState().value
    val loading = viewModel.loadingStateFlow.collectAsState().value
    val error = viewModel.errorStateFlow.collectAsState().value



    if (data.isNotEmpty()){
        AllPatientsLogic(navController,data = data)
    }
    if (loading){
        CircularBar()
    }
    if (error != null){
        ErrorMessage(error = error)
    }

    FloatButton {
        navController.navigate(Screens.AddScreen.route)
    }
}

