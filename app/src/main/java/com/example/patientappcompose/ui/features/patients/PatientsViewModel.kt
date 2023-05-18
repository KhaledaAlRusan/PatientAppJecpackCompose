package com.example.patientappcompose.ui.features.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappcompose.data.repo.PatientRepo
import com.example.patientappcompose.domain.model.delete.DeletePatientRemoteModel
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val repo: PatientRepo):ViewModel() {

    private val _patientMutableStateFlow: MutableStateFlow<List<PatientDataModel>> = MutableStateFlow(emptyList())
    val patientStateFlow = _patientMutableStateFlow.asStateFlow()


    private val _loadingMutableStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow = _loadingMutableStateFlow.asStateFlow()

    private val _errorMutableStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val errorStateFlow = _errorMutableStateFlow.asStateFlow()

    private val _deletePatientStateFlow: MutableLiveData<DeletePatientRemoteModel?> = MutableLiveData()
    val deletePatientStateFlow : LiveData<DeletePatientRemoteModel?> = _deletePatientStateFlow

    init {
        getPatients()
    }

    fun getPatients(){
        viewModelScope.launch {
            _loadingMutableStateFlow.emit(true)
            try {
                _patientMutableStateFlow.emit(repo.getPatients())
            }
            catch (e:Exception)
            {
                _errorMutableStateFlow.emit(e)
            }
            _loadingMutableStateFlow.emit(false)

        }
    }

    fun deletePatient(id:String){
        viewModelScope.launch {
            _loadingMutableStateFlow.emit(true)
            try {
                _deletePatientStateFlow.postValue(repo.deletePatient(id))
            }
            catch (e:Exception)
            {
                _errorMutableStateFlow.emit(e)
            }
            _loadingMutableStateFlow.emit(false)

        }
    }
}