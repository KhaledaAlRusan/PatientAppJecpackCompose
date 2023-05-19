package com.example.patientappcompose.ui.composable.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(size:Int) {
    Spacer(modifier = Modifier.height(size.dp))
}