package com.example.patientappcompose.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.patientappcompose.domain.model.add.AddPatientModel

@Composable
fun AddButton(
    valid:Boolean,
    onClick:() -> Unit
) {
    Button(
        onClick =  {
            if(valid){
                onClick()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Text("Add")
    }
}