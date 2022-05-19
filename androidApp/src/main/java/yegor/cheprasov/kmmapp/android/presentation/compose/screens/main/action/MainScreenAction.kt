package yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.action

sealed class MainScreenAction {
    object Refresh: MainScreenAction()
    object LoadingNextPage: MainScreenAction()
    class ChangeScrollPosition(val newPosition: Int): MainScreenAction()
}
