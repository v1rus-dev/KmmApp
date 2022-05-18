package yegor.cheprasov.kmmapp.android.presentation.compose.screens.main

sealed class MainScreenAction {
    object Refresh: MainScreenAction()
    object LoadingNextPage: MainScreenAction()
}
