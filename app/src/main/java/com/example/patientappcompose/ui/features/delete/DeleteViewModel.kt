package com.example.patientappcompose.ui.features.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientappcompose.data.repo.PatientRepo
import com.example.patientappcompose.domain.model.delete.DeletePatientRemoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteViewModel @Inject constructor(private val repo: PatientRepo): ViewModel() {

    private val _deletePatientStateFlow: MutableLiveData<DeletePatientRemoteModel?> = MutableLiveData()
    val deletePatientStateFlow : LiveData<DeletePatientRemoteModel?> = _deletePatientStateFlow

    private val _loadingMutableStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow = _loadingMutableStateFlow.asStateFlow()

    private val _errorMutableStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val errorStateFlow = _errorMutableStateFlow.asStateFlow()

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