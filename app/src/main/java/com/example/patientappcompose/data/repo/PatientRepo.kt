package com.example.patientappcompose.data.repo

import com.example.patientappcompose.data.datasource.PatientDataSource
import com.example.patientappcompose.domain.model.add.AddPatientModel
import com.example.patientappcompose.domain.model.delete.DeletePatientRemoteModel
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import javax.inject.Inject

class PatientRepo @Inject constructor(private val dataSource: PatientDataSource) {

    suspend fun getPatients():List<PatientDataModel>{
        return dataSource.getPatients().data
    }

    suspend fun addPatient(addPatientModel: AddPatientModel):PatientDataModel{
        return dataSource.addPatient(addPatientModel)
    }

    suspend fun deletePatient(id:String):DeletePatientRemoteModel{
        return dataSource.deletePatient(id)
    }

    suspend fun detailsPatient(id:String): PatientDataModel {
        return dataSource.getPatient(id).data
    }
}