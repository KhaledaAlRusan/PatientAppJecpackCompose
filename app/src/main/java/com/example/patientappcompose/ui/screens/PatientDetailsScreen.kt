package com.example.patientappcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patientappcompose.ui.composable.components.CircularBar
import com.example.patientappcompose.ui.composable.containers.DetailsPatientContainer
import com.example.patientappcompose.ui.composable.containers.ErrorMessage
import com.example.patientappcompose.ui.features.details.DetailsViewModel

@Composable
fun DetailPatientScreen(
    navController: NavController,
    patientId: String = rememberSaveable {
    navController.currentBackStackEntry?.arguments?.getString("patientId").toString()
},
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val patient by viewModel.detailsSuccessStateFlow.collectAsState()
    val loading = viewModel.loadingStateFlow.collectAsState().value
    val error = viewModel.errorStateFlow.collectAsState().value

    LaunchedEffect(patientId) {
        if(patientId != "null") { // checking if patientId is not null
            viewModel.details(patientId)
        }
    }

    if (patient != null) {
        DetailsPatientContainer(patient)
    }
    if (loading) {
        CircularBar()
    }
    if (error != null) {
        ErrorMessage(error = error)
    }
}

