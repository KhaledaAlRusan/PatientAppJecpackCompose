package com.example.patientappcompose.ui.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.patientappcompose.ui.screens.AddPatientScreen
import com.example.patientappcompose.ui.screens.DetailPatientScreen
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
        composable(route = Screens.PatientDetailsScreen.route){backStackEntry ->
            // You can retrieve the patientId like this
            val patientId = backStackEntry.arguments?.getString("patientId")
            DetailPatientScreen(navController, patientId ?: "")
        }
    }
}