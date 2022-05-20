package yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.action

import yegor.cheprasov.kmmapp.android.GamePreview

sealed class MainScreenAction {
    object Refresh: MainScreenAction()
    object LoadingNextPage: MainScreenAction()
    class ChangeScrollPosition(val newPosition: Int): MainScreenAction()
    object ChangeViewType: MainScreenAction()
    class OpenGame(val gamePreview: GamePreview): MainScreenAction()
}
