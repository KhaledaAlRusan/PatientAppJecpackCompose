package com.example.patientappcompose.ui.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import com.example.patientappcompose.ui.features.details.DetailsViewModel
import com.example.patientappcompose.ui.features.patients.PatientsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PatientList(
    data: List<PatientDataModel>,
    patientViewModel: PatientsViewModel,
    detailsViewModel: DetailsViewModel,
    onClick: (PatientDataModel) -> Unit,
    onDelete: (PatientDataModel) -> Unit
) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = data.size,
            key = { index ->
                data[index].id
            }
        ) { index ->
            Box(modifier = Modifier.animateItemPlacement()) {
                PatientRow(
                    record = data[index],
                    onClick = { onClick(data[index]) },
                    selectedPatientId = patientViewModel.selectedPatientId,
                ) { onDelete(data[index]) }
            }
        }
    }
}
