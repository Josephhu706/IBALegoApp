package com.example.legosearchapp.ui
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.legosearchapp.LegoSearchApplication
import com.example.legosearchapp.ui.screens.LegoAppViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer{
            LegoAppViewModel(
                legoSearchApplication().dataStoreRepository
            )
        }
    }

}

fun CreationExtras.legoSearchApplication(): LegoSearchApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LegoSearchApplication)