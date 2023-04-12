package com.example.legosearchapp

import LegoSearchNavHost
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.legosearchapp.ui.navigation.NavigationDestination
import com.example.legosearchapp.ui.navigation.SetInfoDestination
import com.example.legosearchapp.ui.screens.LegoAppViewModel
import com.example.legosearchapp.utils.LegoSearchContentType

@Composable
fun LegoSearchApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: LegoAppViewModel
){
    LegoSearchNavHost(
        navController = navController,
        viewModel = viewModel
    )
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
){
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = currentDestination.titleRes)
            )
        },
        navigationIcon = {
            if(canNavigateBack
                && currentDestination == SetInfoDestination
                && contentType == LegoSearchContentType.ListOnly){
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
                onCheckedChange = onToggleDarkTheme,
            )
        },
        modifier = modifier
    )
}

@Composable
fun darkModeToggle(
    darkModeState: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    if(isSystemInDarkTheme()){
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
        checked = darkModeState,
        onCheckedChange = {
            onCheckedChange(it)
        }
    )
}