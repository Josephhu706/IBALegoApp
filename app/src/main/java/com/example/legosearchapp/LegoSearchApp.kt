package com.example.legosearchapp

import LegoSearchNavHost

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.legosearchapp.ui.navigation.Destinations
import com.example.legosearchapp.ui.navigation.NavigationDestination
import com.example.legosearchapp.ui.screens.LegoAppLoadingState
import com.example.legosearchapp.ui.screens.LegoAppViewModel
import com.example.legosearchapp.utils.LegoSearchContentType

@Composable
fun LegoSearchApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: LegoAppViewModel,
    windowSize: WindowWidthSizeClass,
){
    val contentType = when (windowSize){
        WindowWidthSizeClass.Compact -> LegoSearchContentType.ListOnly
        WindowWidthSizeClass.Medium -> LegoSearchContentType.ListAndDetail
        WindowWidthSizeClass.Expanded -> LegoSearchContentType.ListAndDetail
        else -> LegoSearchContentType.ListOnly
    }

    if(contentType == LegoSearchContentType.ListOnly){
        LegoSearchNavHost(navController = navController, viewModel = viewModel)
    } else {
        //list and info page for big screens
    }
}

@Composable
fun LegoSearchTopAppBar(
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    currentDestination: NavigationDestination,
    canNavigateBack: Boolean,
    contentType: LegoSearchContentType,
    onToggleDarkTheme: (Boolean) -> Unit,
    darkModeState: Boolean,
    loadingState: LegoAppLoadingState
){
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = currentDestination.titleRes)
            )
        },
        navigationIcon = {
            if(canNavigateBack && contentType == LegoSearchContentType.ListOnly){
                IconButton(
                    onClick = { onBackButtonClick() },
                    modifier = Modifier.testTag("backButton")
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            darkModeToggle(
                darkModeState = darkModeState,
                onDarkModeToggle = onToggleDarkTheme,
                loadingState = loadingState
            )
        },
        modifier = modifier.testTag(stringResource(id = R.string.topBar))
    )
}

@Composable
fun darkModeToggle(
    darkModeState: Boolean,
    onDarkModeToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    loadingState: LegoAppLoadingState
){
    if(darkModeState){
        Text(text = stringResource(
            id = R.string.toggleDarkMode,
        ),
            style = MaterialTheme.typography.subtitle1
        )
    }else{
        Text(text = stringResource(
            id = R.string.toggleLightMode,
        ),
            style = MaterialTheme.typography.subtitle1
        )
    }

    Spacer(modifier = Modifier.padding(start = 4.dp))
    Switch(
        enabled = loadingState == LegoAppLoadingState.Success,
        checked = darkModeState,
        onCheckedChange = {
            onDarkModeToggle(!darkModeState)
        },
        modifier = modifier.testTag(stringResource(id = R.string.darkModeToggle))
    )
}

@Preview
@Composable
fun LegoAppTopBarPreview(){
    LegoSearchTopAppBar(
        onBackButtonClick = {},
        currentDestination = Destinations.SearchScreenDestination,
        canNavigateBack = true,
        contentType = LegoSearchContentType.ListOnly,
        onToggleDarkTheme = {},
        darkModeState = false,
        loadingState = LegoAppLoadingState.Success
    )
}