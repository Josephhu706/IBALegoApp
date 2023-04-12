package com.example.legosearchapp

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.legosearchapp.ui.screens.LegoAppViewModel

@Composable
fun LegoSearchApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: LegoAppViewModel
){

}