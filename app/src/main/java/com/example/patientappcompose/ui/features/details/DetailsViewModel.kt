package com.example.patientappcompose.ui.features.details

import android.util.Log
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
class DetailsViewModel @Inject constructor(private val repo: PatientRepo): ViewModel() {

    private val _detailsSuccessStateFlow:MutableStateFlow<PatientDataModel?> = MutableStateFlow(null)
    val detailsSuccessStateFlow = _detailsSuccessStateFlow.asStateFlow()

    private val _loadingMutableStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow = _loadingMutableStateFlow.asStateFlow()

    private val _errorMutableStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val errorStateFlow = _errorMutableStateFlow.asStateFlow()

    fun details(id: String) {

        viewModelScope.launch {
            _loadingMutableStateFlow.emit(true)
            try {
                _detailsSuccessStateFlow.emit(repo.detailsPatient(id))
                Log.d("working!","Working")
            }
            catch (e:Exception){
                _errorMutableStateFlow.emit(e)
            }
            _loadingMutableStateFlow.emit(false)
        }
    }

}