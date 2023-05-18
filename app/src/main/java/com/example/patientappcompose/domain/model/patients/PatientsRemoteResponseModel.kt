package com.example.patientappcompose.domain.model.patients

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatientsRemoteResponseModel(
    @Json(name = "data")
    val `data`: List<PatientDataModel>,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)
