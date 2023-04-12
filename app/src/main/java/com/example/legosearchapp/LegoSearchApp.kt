package com.example.legosearchapp

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.legosearchapp.ui.screens.LegoAppViewModel

@Composable
fun LegoSearchApp(){
    val viewModel: LegoAppViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
}