package com.example.patientappcompose.ui.composable.containers

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.patientappcompose.R
import com.example.patientappcompose.ui.composable.components.AddButton
import com.example.patientappcompose.ui.composable.components.EditText

@Composable
fun AddPatientContainer(
    onAddClicked:(list:MutableList<String>) ->Unit
) {
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
                onAddClicked(listOf(name,email,address,birthdate,gender,mobile) as MutableList<String>)
            }
        }
    }
}