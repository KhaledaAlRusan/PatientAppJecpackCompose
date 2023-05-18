package com.example.patientappcompose.ui.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import com.example.patientappcompose.ui.screens.AddPatientScreen
import com.example.patientappcompose.ui.screens.PatientsScreen

@Composable
fun Navigation( ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route){
        composable(route = Screens.MainScreen.route){
            PatientsScreen( navController = navController)
        }
        composable(route = Screens.AddScreen.route){
            AddPatientScreen(navController)
        }
    }
}