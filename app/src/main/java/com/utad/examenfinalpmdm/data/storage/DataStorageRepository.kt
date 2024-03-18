package com.utad.examenfinalpmdm.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreRepository {

    // Inicializo el data store
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MY_DATA_STORE")

    // Key con la que voy acceder a los datos
    val sampleKey = stringPreferencesKey("TASK_KEY")

    // Funcion para almacenar los datos en local necesita dato , contexto Y key
    suspend fun saveData(context: Context, sampleData: String) {
        context.dataStore.edit { editor ->
            editor[sampleKey] = sampleData
        }
    }

    // Recupero los datos necesito el contexto y la key
    suspend fun getSampleData(context: Context): Flow<String> {
        return context.dataStore.data.map { editor ->
            editor[sampleKey] ?: "No hay datos"
        }
    }


}