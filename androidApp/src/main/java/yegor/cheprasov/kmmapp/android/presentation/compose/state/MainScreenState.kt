package yegor.cheprasov.kmmapp.android.presentation.compose.state

import yegor.cheprasov.kmmapp.android.GamePreview

sealed class MainScreenState {
    class Success(
        val gameList: List<GamePreview>
    ): MainScreenState()

    object Loading: MainScreenState()

    object Error: MainScreenState()
}
