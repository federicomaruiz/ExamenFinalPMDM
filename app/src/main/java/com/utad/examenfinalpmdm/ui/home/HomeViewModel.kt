package com.utad.examenfinalpmdm.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.examenfinalpmdm.data.network.TaskApi
import com.utad.examenfinalpmdm.data.storage.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var _userName: MutableLiveData<String?> = MutableLiveData(null)
    val userName: LiveData<String?> = _userName

    private var _uiState: MutableLiveData<HomeUIState> = MutableLiveData(HomeUIState(true))
    val uiState: LiveData<HomeUIState> get() = _uiState

    // Pongo a la escucha de cuando cambie la informacion
    fun getUserName(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            DataStoreRepository.getSampleData(context).collect { userName ->
                if (userName != "No hay datos") {
                    _userName.postValue(userName)
                }
            }
        }
    }

    fun getHomeWork(userName: String) {
        _uiState.postValue(HomeUIState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            val response = TaskApi.service.getHomeWork("Federico", userName)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _uiState.postValue(HomeUIState(schoolList = responseBody))
                } else {
                    _uiState.postValue(HomeUIState(isError = true))
                }
            } else {
                _uiState.postValue(HomeUIState(isError = true))
            }
        }
    }

}