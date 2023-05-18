package com.example.patientappcompose.ui.screens


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.patientappcompose.R
import com.example.patientappcompose.domain.model.add.AddPatientModel
import com.example.patientappcompose.ui.composable.AddButton
import com.example.patientappcompose.ui.composable.CircularBar
import com.example.patientappcompose.ui.composable.EditText
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

    AddPatientContent(viewModel = viewModel)

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

@Composable
@Preview(showSystemUi = true)
fun PreviewAddPatient(){
   AddPatientScreen()
}
@Composable
fun AddPatientContent(viewModel: AddPatientViewModel){
    val state = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var birthdate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }

    val isFormValid by remember {
        derivedStateOf {
            listOf(name, email, address, birthdate, gender, mobile).all { it.isNotEmpty() }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(state),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 35.dp)
                    .clip(RoundedCornerShape(50))
            )
            EditText(header = "Name", text = name, onTextChanged = {name = it})
            EditText(header = "Email", text = email, onTextChanged = {email = it})
            EditText(header = "Address", text = address, onTextChanged = {address = it})
            EditText(header = "Birthdate", text = birthdate, onTextChanged = {birthdate = it})
            EditText(header = "Gender", text = gender, onTextChanged = {gender = it})
            EditText(header = "Mobile", text = mobile, onTextChanged = {mobile = it})
            AddButton(valid = isFormValid) {
                viewModel.addPatient(AddPatientModel(
                    name, address, gender, birthdate, mobile, email
                ))
            }
        }
    }
}