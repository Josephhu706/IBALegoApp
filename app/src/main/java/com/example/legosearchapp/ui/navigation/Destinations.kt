package com.example.legosearchapp.ui.navigation

import com.example.legosearchapp.R

class Destinations {
    object SplashScreenDestination : NavigationDestination{
        override val route = "splash"
        override val titleRes = R.string.splashScreen
    }
    object SearchScreenDestination : NavigationDestination{
        override val route = "search"
        override val titleRes = R.string.appName
    }

    object SetInfoDestination : NavigationDestination{
        override val route = "sets"
        override val titleRes = R.string.setInformation
    }
}
