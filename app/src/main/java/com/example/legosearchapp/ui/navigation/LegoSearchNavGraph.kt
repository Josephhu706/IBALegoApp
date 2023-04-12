import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.legosearchapp.ui.navigation.SearchScreenDestination
import com.example.legosearchapp.ui.navigation.SetInfoDestination
import com.example.legosearchapp.ui.screens.LegoAppViewModel
import kotlinx.coroutines.launch

@Composable
fun LegoSearchNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: LegoAppViewModel
){
    val coroutineScope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = SearchScreenDestination.route,
        modifier = modifier ){
        composable(route = SearchScreenDestination.route){

        }
        composable(route = SetInfoDestination.route){
        }
    }
}