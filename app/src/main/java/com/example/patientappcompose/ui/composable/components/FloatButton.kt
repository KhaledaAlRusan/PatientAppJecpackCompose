package com.example.patientappcompose.ui.composable.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.patientappcompose.ui.theme.brightGreen

@Composable
fun FloatButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize().padding(32.dp),
        contentAlignment = Alignment.BottomEnd
    ){
        FloatingActionButton(
            onClick = onClick,
            containerColor = brightGreen,
            shape = RoundedCornerShape(16.dp),
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add FAB",
                tint = Color.White,
            )
        }
    }
}