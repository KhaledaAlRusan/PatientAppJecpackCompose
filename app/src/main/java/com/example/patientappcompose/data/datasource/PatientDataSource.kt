package com.example.patientappcompose.data.datasource

import com.example.patientappcompose.domain.model.add.AddPatientModel
import com.example.patientappcompose.domain.model.delete.DeletePatientRemoteModel
import com.example.patientappcompose.domain.model.details.DetailsPatientRemoteModel
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import com.example.patientappcompose.domain.model.patients.PatientsRemoteResponseModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientDataSource {
    @GET("patients")
    suspend fun getPatients(): PatientsRemoteResponseModel

    @POST("patients")
    suspend fun addPatient(@Body addPatientModel: AddPatientModel): PatientDataModel

    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id:String): DeletePatientRemoteModel

    @GET("patients/{id}")
    suspend fun getPatient(@Path("id") id: String): DetailsPatientRemoteModel
}