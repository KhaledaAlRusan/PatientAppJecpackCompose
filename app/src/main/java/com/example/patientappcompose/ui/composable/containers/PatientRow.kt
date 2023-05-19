package com.example.patientappcompose.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.patientappcompose.R
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import com.example.patientappcompose.ui.theme.brightBlue
import com.example.patientappcompose.ui.theme.darkBlue

@SuppressLint("UnrememberedMutableState")
@Composable
fun PatientRow(
    record: PatientDataModel,
    modifier: Modifier = Modifier,
    selectedPatientId: String?,
    onClick:(id:String) ->Unit = {},
    onDelete: (id:String) -> Unit = {}
){
    val isSelected = selectedPatientId == record.id
    Box(modifier = Modifier.wrapContentSize(),){
        Card(
            modifier = modifier.wrapContentHeight().clickable {
                onClick(record.id)
            },
            shape = RoundedCornerShape(16.dp),
            colors = selected(isSelected)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = record.photo ),
                    contentDescription = "Patient Image",
                    modifier = Modifier.aspectRatio(4f/3f)
                )
                VerticalSpacer(size = 8)
                Text(text = record.name)
                VerticalSpacer(size = 8)

                Text(text = record.birthdate)
                VerticalSpacer(size = 8)
                Text(text = record.condition)

            }
        }
        Image(
            painter = painterResource( R.drawable.ic_delete),
            contentDescription = "Delete Item",
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp).clickable { onDelete(record.id) }
        )
    }
}
@Composable
fun selected(clicked: Boolean):CardColors{
    return if (clicked){
        CardDefaults.cardColors(containerColor = darkBlue)
    }
    else{
        CardDefaults.cardColors(containerColor = brightBlue)
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewPatientRow(){
    val patientData = PatientDataModel(
        address = "123 Main St",
        birthdate = "1990-01-01",
        condition = "Healthy",
        createdAt = "2023-05-18T12:34:56Z",
        email = "example@example.com",
        gender = "Male",
        id = "1234567890",
        mobile = "123-456-7890",
        name = "John Doe",
        photo = "https://example.com/photo.jpg",
        tests = listOf(),
        updatedAt = "2023-05-18T12:34:56Z",
        v = 1
    )
    PatientRow(record = patientData, selectedPatientId = null)
}