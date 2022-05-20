package yegor.cheprasov.kmmapp.android.presentation.compose.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.getViewModel
import yegor.cheprasov.kmmapp.android.presentation.compose.components.MainAppbar
import yegor.cheprasov.kmmapp.android.presentation.compose.navigation.navigateToDetailsGame
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.action.MainScreenAction
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.fake.getMainFakeScreenSuccess
import yegor.cheprasov.kmmapp.android.presentation.compose.state.MainScreenState
import yegor.cheprasov.kmmapp.android.presentation.viewModel.MainViewModel
import yegor.cheprasov.kmmapp.android.utils.ViewType

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = getViewModel()
) {
    val mainState = viewModel.mainState.collectAsState()
    val refreshState = viewModel.refreshState.collectAsState()
    val scrollPosition = viewModel.scrollUp.collectAsState()
    val viewType = viewModel.viewType.collectAsState()
    MainScreen(
        state = mainState.value,
        isRefresh = refreshState.value,
        scrollUpState = scrollPosition,
        searchText = "",
        viewType = viewType.value,
        onAction = { action ->
            when (action) {
                MainScreenAction.Refresh -> {
                    viewModel.refresh()
                }
                MainScreenAction.LoadingNextPage -> {
                    viewModel.downloadNextPage()
                }
                MainScreenAction.ChangeViewType -> {
                    viewModel.changeViewType()
                }
                is MainScreenAction.ChangeScrollPosition -> {
                    viewModel.updateScrollPosition(action.newPosition)
                }
                is MainScreenAction.OpenGame -> {
                    navController.navigateToDetailsGame(
                        action.gamePreview.id.toString(),
                        action.gamePreview.backgroundImage,
                        action.gamePreview.name
                    )
                }
            }
        }
    )
}

@Composable
fun MainScreen(
    state: MainScreenState,
    isRefresh: Boolean,
    scrollUpState: State<Boolean>,
    searchText: String,
    viewType: ViewType,
    onAction: (MainScreenAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    )
    {
        Box() {
            when (state) {
                MainScreenState.Error -> {

                }
                MainScreenState.Loading -> {

                }
                is MainScreenState.Success -> {
                    SuccessGameList(
                        list = state.gameList,
                        isRefreshing = isRefresh,
                        viewType = viewType,
                        onAction = onAction
                    )
                }
            }
            MainAppbar(
                onTextChanged = {},
                onClickFilter = {},
                onClickViewType = {
                    onAction(MainScreenAction.ChangeViewType)
                },
                viewType = viewType,
                scrollUpState = scrollUpState,
                text = searchText
            )
        }

    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(
        state = getMainFakeScreenSuccess(),
        isRefresh = false,
        searchText = "",
        viewType = ViewType.MINI,
        scrollUpState = MutableStateFlow(false).collectAsState(),
        onAction = {}
    )
}

