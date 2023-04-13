import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.legosearchapp.ui.navigation.Destinations
import com.example.legosearchapp.ui.screens.LegoAppViewModel
import com.example.legosearchapp.ui.screens.searchScreen.LegoSearchScreen
import kotlinx.coroutines.launch

@Composable
fun LegoSearchNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: LegoAppViewModel
){
    val uiState = viewModel.uiState.collectAsState().value
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: Destinations.SearchScreenDestination.route
    val coroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = Destinations.SearchScreenDestination.route,
        modifier = modifier
    ){
        composable(route = Destinations.SearchScreenDestination.route){
            LegoSearchScreen(
                navigateToSetOnClick = {},
                currentDestination = Destinations.SearchScreenDestination,
                onToggleDarkTheme = {

                },
                uiState = uiState
            )
        }
        composable(route = Destinations.SetInfoDestination.route){
        }
    }
}