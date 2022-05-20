package yegor.cheprasov.kmmapp.android.presentation.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGame.DetailsGame
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.MainScreen

@Composable
fun NavController(navController: NavHostController, startDestination: String = "main") {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("main") { MainScreen(navController) }
        composable(
            "detailsGame/{gameId}?imageUrl={imageUrl}?gameName={gameName}",
            arguments = listOf(
                navArgument("gameId") { type = NavType.StringType },
                navArgument("imageUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.let { bundle ->
                val gameId = bundle.getString("gameId")
                val imageUrl = bundle.getString("imageUrl")
                val gameName = bundle.getString("gameName")
                gameId?.let {
                    DetailsGame(
                        navController = navController,
                        gameId = gameId,
                        gameName = gameName,
                        imageUrl = imageUrl
                    )
                }
            }
        }
    }
}

fun NavController.navigateToDetailsGame(gameId: String, imageUrl: String?, gameName: String) =
    this.navigate("detailsGame/$gameId?imageUrl=$imageUrl?gameName=$gameName")