package yegor.cheprasov.kmmapp.android.presentation.compose.actions

sealed class MainScreenAction {
    object Refresh: MainScreenAction()
    object LoadingNextPage: MainScreenAction()
}
