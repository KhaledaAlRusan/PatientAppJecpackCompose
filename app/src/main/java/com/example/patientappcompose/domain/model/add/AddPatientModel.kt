package com.example.patientappcompose.domain.model.add

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddPatientModel(
    @Json( name = "name")
    val name:String,
    @Json( name = "address")
    val address:String,
    @Json( name = "gender")
    val gender:String,
    @Json( name = "birthdate")
    val birthdate:String,
    @Json( name = "mobile")
    val mobile:String,
    @Json( name = "email")
    val email:String,
)