package com.example.patientappcompose.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.patientappcompose.ui.composable.CircularBar
import com.example.patientappcompose.ui.composable.CustomRatingBar
import com.example.patientappcompose.ui.features.details.DetailsViewModel

@Composable
fun PatientDetailScreen(
    navController: NavController?,
    patientId: String = rememberSaveable {
    navController?.currentBackStackEntry?.arguments?.getString("patientId").toString()
},
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val patient by viewModel.detailsSuccessStateFlow.collectAsState()
    val loading = viewModel.loadingStateFlow.collectAsState().value
    val error = viewModel.errorStateFlow.collectAsState().value

    LaunchedEffect(patientId) {
        if(patientId != "null") { // checking if patientId is not null
            viewModel.details(patientId)
        }
    }

    if (patient != null) {
        val image: Painter = rememberAsyncImagePainter(model = patient!!.photo)

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (viewBackground, textView, cardViewImage, ratingBar, tvAbout, tvAboutInfo) = createRefs()

            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .constrainAs(viewBackground) {
                        top.linkTo(parent.top)
                    }
            )

            Text(
                text = patient!!.name,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.constrainAs(textView) {
                    top.linkTo(viewBackground.top, margin = 30.dp)
                    start.linkTo(parent.start, margin = 60.dp)
                }
            )

            Card(
                modifier = Modifier
                    .size(150.dp)
                    .constrainAs(cardViewImage) {
                        top.linkTo(viewBackground.bottom)
                        bottom.linkTo(viewBackground.bottom)
                        start.linkTo(parent.start, margin = 60.dp)
                    },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(4.dp, Color.White)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = image,
                    contentDescription = "Patient Image",
                    contentScale = ContentScale.FillBounds
                )
            }

            // Place your custom rating bar here
            Box(
                modifier = Modifier.size(30.dp).constrainAs(ratingBar) {
                bottom.linkTo(viewBackground.bottom, margin = 10.dp)
                start.linkTo(cardViewImage.end, margin = 10.dp) }
            ){
                CustomRatingBar(
                    modifier = Modifier.fillMaxSize().scale(0.8f),
                    rating = 4f,
                    spaceBetween = 3.dp
                )
            }

            Text(
                text = "About:",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.DarkGray,
                modifier = Modifier.constrainAs(tvAbout) {
                    top.linkTo(cardViewImage.bottom, margin = 10.dp)
                    start.linkTo(cardViewImage.start)
                }
            )
            Text(
                text = patient!!.getPatientInfo(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                modifier = Modifier.constrainAs(tvAboutInfo) {
                    top.linkTo(tvAbout.bottom, margin = 10.dp)
                    start.linkTo(tvAbout.start,margin = 6.dp)
                    end.linkTo(parent.end, margin = 5.dp)
                    width = Dimension.fillToConstraints
                }
            )
        }
    }
    if (loading) {
        CircularBar()
    }
    if (error != null) {
        ErrorMessage(error = error)
    }
}

