package com.example.legosearchapp.ui.screens.searchScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.legosearchapp.LegoSearchTopAppBar
import com.example.legosearchapp.R
import com.example.legosearchapp.model.Set
import com.example.legosearchapp.ui.navigation.NavigationDestination
import com.example.legosearchapp.ui.screens.LegoSearchAppUiState
import com.example.legosearchapp.utils.LegoSearchContentType

@Composable
fun LegoSearchScreen(
    currentDestination: NavigationDestination,
    modifier: Modifier = Modifier,
    navigateToSetOnClick: (Set) -> Unit,
    onToggleDarkTheme: (Boolean) -> Unit,
    uiState: LegoSearchAppUiState
){
    Scaffold(
        modifier = modifier.testTag(stringResource(id = R.string.searchScreen)),
        topBar = {
            LegoSearchTopAppBar(
                onBackButtonClick = {},
                currentDestination = currentDestination,
                canNavigateBack = false,
                contentType = LegoSearchContentType.ListOnly,
                onToggleDarkTheme = onToggleDarkTheme,
                darkModeState = uiState.isDarkTheme,
                loadingState = uiState.dataStoreLoadingState
            )
        }
    ){
        SearchScreenBody(
            modifier = modifier.padding(it)
        )
    }
}

