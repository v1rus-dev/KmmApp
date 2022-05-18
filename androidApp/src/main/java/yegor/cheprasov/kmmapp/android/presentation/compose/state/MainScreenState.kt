package yegor.cheprasov.kmmapp.android.presentation.compose.state

import yegor.cheprasov.kmmapp.data.entities.GamePreview

sealed class MainScreenState {
    class Success(
        val gameList: List<GamePreview>
    ): MainScreenState()

    object Loading: MainScreenState()

    object ErrorL: MainScreenState()
}
