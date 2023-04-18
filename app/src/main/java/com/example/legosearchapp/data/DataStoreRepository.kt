package com.example.legosearchapp.data

import android.content.ContentValues
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

interface PreferencesRepository {
    suspend fun  setIsDarkMode(isDarkMode: Boolean)
    fun getDarkMode(): Flow<Boolean>
}

class DataStoreRepository(
private val dataStore: DataStore<Preferences>
) : PreferencesRepository{

    companion object{
        var isDarkModeKey = booleanPreferencesKey("IS_DARK_MODE")
    }

    override suspend fun setIsDarkMode(isDarkMode: Boolean){
        dataStore.edit { preferences ->
            preferences[isDarkModeKey] = isDarkMode
        }
    }

    override fun getDarkMode(): Flow<Boolean> {
        return dataStore.data
            .catch {
                if(it is IOException){
                    Log.e(ContentValues.TAG, "Error reading preferences", it)
                } else {
                    throw it
                }
            }
            .map{preferences ->
                preferences[isDarkModeKey] ?: false
            }
    }
}