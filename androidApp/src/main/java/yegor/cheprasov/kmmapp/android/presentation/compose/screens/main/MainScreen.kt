package yegor.cheprasov.kmmapp.android.presentation.compose.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.getViewModel
import yegor.cheprasov.kmmapp.android.presentation.compose.actions.MainScreenAction
import yegor.cheprasov.kmmapp.android.presentation.compose.fake.getMainFakeScreenSuccess
import yegor.cheprasov.kmmapp.android.presentation.compose.state.MainScreenState
import yegor.cheprasov.kmmapp.android.presentation.viewModel.MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = getViewModel()
) {
    val mainState = viewModel.mainState.collectAsState()
    val refreshState = viewModel.refreshState.collectAsState()
    MainScreen(
        state = mainState.value,
        isRefresh = refreshState.value,
        onAction = { action ->
            when(action) {
                MainScreenAction.Refresh -> {
                    viewModel.refresh()
                }
            }
        }
    )
}

@Composable
fun MainScreen(
    state: MainScreenState,
    isRefresh: Boolean,
    onAction: (MainScreenAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    )
    {
        when (state) {
            MainScreenState.ErrorL -> {

            }
            MainScreenState.Loading -> {

            }
            is MainScreenState.Success -> {
                val games = state.gameList.collectAsLazyPagingItems()
                SuccessGameList(
                    list = games,
                    isRefreshing = isRefresh,
                    onRefresh = onAction
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(
        state = getMainFakeScreenSuccess(),
        isRefresh = false,
        onAction = {}
    )
}

