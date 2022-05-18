package yegor.cheprasov.kmmapp.android.presentation.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.MainScreen

@Composable
fun NavController(navController: NavHostController, startDestination: String = "main") {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("main") { MainScreen(navController) }
    }
}