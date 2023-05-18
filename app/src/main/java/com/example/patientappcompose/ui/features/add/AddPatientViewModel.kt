package com.example.patientappcompose.ui.features.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappcompose.data.repo.PatientRepo
import com.example.patientappcompose.domain.model.add.AddPatientModel
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import com.example.patientappcompose.domain.model.patients.PatientsRemoteResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(
    private val repo: PatientRepo,
): ViewModel() {

    private val _addPatientsMutableStateFlow: MutableStateFlow<PatientDataModel?> = MutableStateFlow(null)
    val addPatientsStateFlow = _addPatientsMutableStateFlow.asStateFlow()


    private val _loadingMutableStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow = _loadingMutableStateFlow.asStateFlow()

    private val _errorMutableStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val errorStateFlow = _errorMutableStateFlow.asStateFlow()


    fun addPatient(addPatientModel: AddPatientModel){
        viewModelScope.launch {
            _loadingMutableStateFlow.emit(true)
            try {
                _addPatientsMutableStateFlow.emit(repo.addPatient(addPatientModel))
            }
            catch (e:Exception)
            {
                _errorMutableStateFlow.emit(e)
            }
            _loadingMutableStateFlow.emit(false)
        }
    }

}