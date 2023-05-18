package com.example.patientappcompose.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(header:String, text:String, onTextChanged: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = {newValue ->
            onTextChanged(newValue)
                        },
        label = { Text(header) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    )
}