package yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGameMultiplatform

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGame.DetailsGameAction
import yegor.cheprasov.kmmapp.components.root.Root

@Composable
fun DetailsGame(
    navController: NavHostController,
    componentPage: Root
) {
    Scaffold {
        Children(routerState = componentPage.routerState) {
            when(val child = it.instance) {
                is Root.Child.Page -> {
                    DetailsGame(navController = navController, componentPage = componentPage)
                }
            }
        }
    }
}

@Composable
fun DetailsGame(
    onAction: (DetailsGameAction) -> Unit
) {

}