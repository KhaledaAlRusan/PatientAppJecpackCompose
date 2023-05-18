package com.example.patientappcompose.ui.screens



import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patientappcompose.ui.composable.AllPatients
import com.example.patientappcompose.ui.composable.CircularBar
import com.example.patientappcompose.ui.composable.FloatButton
import com.example.patientappcompose.ui.features.patients.PatientsViewModel
import com.example.patientappcompose.ui.navigator.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PatientsScreen(
    navController: NavController? = null,
    viewModel:PatientsViewModel = hiltViewModel()
) {
    val data = viewModel.patientStateFlow.collectAsState().value
    val loading = viewModel.loadingStateFlow.collectAsState().value
    val error = viewModel.errorStateFlow.collectAsState().value



    if (data.isNotEmpty()){
        AllPatients(data = data)
    }
    if (loading){
        CircularBar()
    }
    if (error != null){
        ErrorMessage(error = error)
    }

    FloatButton {
        navController?.navigate(Screens.AddScreen.route)
    }
}

