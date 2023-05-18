package com.example.patientappcompose.domain.model.delete

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeletePatientRemoteModel (
    @Json(name = "status")
    val status:Int,
    @Json(name = "message")
    val message:String,
)