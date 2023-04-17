package com.example.legosearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legosearchapp.ui.AppViewModelProvider
import com.example.legosearchapp.ui.screens.LegoAppLoadingState
import com.example.legosearchapp.ui.screens.LegoAppViewModel
import com.example.legosearchapp.ui.theme.LegoSearchAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: LegoAppViewModel = viewModel(
                factory = AppViewModelProvider.Factory
            )
            val uiState = viewModel.uiState.collectAsState().value
            LegoSearchAppTheme(
                uiState.isDarkTheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    LegoSearchApp(
                        viewModel = viewModel,
                        windowSize = windowSize.widthSizeClass,
                    )
                }
            }
        }
    }
}
