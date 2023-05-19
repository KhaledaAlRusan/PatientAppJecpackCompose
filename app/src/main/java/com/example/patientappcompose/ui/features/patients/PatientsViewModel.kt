package com.example.patientappcompose.ui.features.patients

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappcompose.data.repo.PatientRepo
import com.example.patientappcompose.domain.model.patients.PatientDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val repo: PatientRepo):ViewModel() {

    var selectedPatientId by mutableStateOf<String?>(null)

    private val _patientMutableStateFlow: MutableStateFlow<List<PatientDataModel>> = MutableStateFlow(emptyList())
    val patientStateFlow = _patientMutableStateFlow.asStateFlow()


    private val _loadingMutableStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow = _loadingMutableStateFlow.asStateFlow()

    private val _errorMutableStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val errorStateFlow = _errorMutableStateFlow.asStateFlow()


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
}