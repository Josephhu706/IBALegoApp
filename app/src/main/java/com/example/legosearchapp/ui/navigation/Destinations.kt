package com.example.legosearchapp.ui.navigation

import com.example.legosearchapp.R

class Destinations {
    object SearchScreenDestination : NavigationDestination{
        override val route = "search"
        override val titleRes = R.string.app_name
    }

    object SetInfoDestination : NavigationDestination{
        override val route = "sets"
        override val titleRes = R.string.set_information
    }
}
