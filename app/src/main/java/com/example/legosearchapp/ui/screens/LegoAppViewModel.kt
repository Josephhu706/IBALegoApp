package com.example.legosearchapp.ui.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.legosearchapp.LegoSearchApplication
import com.example.legosearchapp.data.DataStoreRepository
import com.example.legosearchapp.model.Theme
import com.example.legosearchapp.model.Set
import com.example.legosearchapp.utils.readCSV
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LegoAppViewModel(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel(){

    var legoSearchContext: Context = LegoSearchApplication.context!!

    private val _uiState = MutableStateFlow(
        LegoSearchAppUiState(
            setData = readCSV("sets.csv", legoSearchContext, csvType = Set::class),
            themeData = readCSV("themes.csv", legoSearchContext, csvType = Theme::class),
        )
    )

    val uiState = _uiState.asStateFlow()

    init {
        setDarkThemeToState()
    }

    private fun setDarkThemeToState(){
        viewModelScope.launch {
            dataStoreRepository.getDarkMode().collect { isDarkTheme ->
                _uiState.update {
                    it.copy(
                        isDarkTheme = isDarkTheme
                    )
                }
            }
        }
    }

    fun saveDarkModeState(isDarkTheme: Boolean){
        viewModelScope.launch{
            dataStoreRepository.setIsDarkMode(isDarkTheme)
            setDarkThemeToState()
        }
    }
}

data class LegoSearchAppUiState(
    val setData: List<Set> = emptyList(),
    val themeData: List<Theme> = emptyList(),
    val searchHistory: MutableList<Set> = mutableListOf<Set>(),
    val filteredSets: List<Set> = emptyList(),
    val currentSet: Set? = null,
    val searchTerm: String = "",
    val isDarkTheme: Boolean = false
)