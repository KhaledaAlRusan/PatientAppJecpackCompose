package com.example.patientappcompose.domain.model.details

import com.example.patientappcompose.domain.model.patients.PatientDataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailsPatientRemoteModel(
    @Json(name = "data")
    val `data`: PatientDataModel,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)
