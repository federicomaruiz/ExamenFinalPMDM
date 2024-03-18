package com.utad.examenfinalpmdm.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.examenfinalpmdm.data.storage.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // Función que se llama al repository dentro de una corrutina en el hilo secundario
    // para almacenar el usuario, necesita el contexto y el valor.
    fun saveUserName(context: Context, userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            DataStoreRepository.saveData(context, userName)
        }
    }
}