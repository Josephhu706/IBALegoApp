package com.example.legosearchapp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.legosearchapp.data.DataStoreRepository

private const val SAVED_DATA = "saved_data"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SAVED_DATA
)
class LegoSearchApplication : Application() {
    lateinit var dataStoreRepository: DataStoreRepository
    override fun onCreate() {
        super.onCreate()
        dataStoreRepository = DataStoreRepository(dataStore)
        context = this
    }
    companion object {
        var context: Context? = null
            internal set
    }
}