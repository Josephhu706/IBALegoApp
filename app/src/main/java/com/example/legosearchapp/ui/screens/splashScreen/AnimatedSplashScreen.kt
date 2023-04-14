package com.example.legosearchapp.ui.screens.splashScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.legosearchapp.R
import com.example.legosearchapp.ui.navigation.Destinations
import com.example.legosearchapp.ui.screens.LegoSearchAppUiState
import com.example.legosearchapp.ui.theme.Purple700
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    uiState: LegoSearchAppUiState,
    navController: NavController
){
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Destinations.SearchScreenDestination.route)
    }
    Splash(
        uiState = uiState,
        alpha = alphaAnim.value
    )
}

@Composable
fun Splash(
    modifier: Modifier = Modifier,
    uiState: LegoSearchAppUiState,
    alpha: Float
){
    Box(
        modifier = modifier
            .background(if (uiState.isDarkTheme) Color.Black else Purple700)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = modifier
                .size(120.dp)
                .alpha(alpha)
            ,
            imageVector = ImageVector.vectorResource(id = R.drawable.legobrick),
            contentDescription = stringResource(id = R.string.splashScreen),
            tint = Color.White
        )
    }
}