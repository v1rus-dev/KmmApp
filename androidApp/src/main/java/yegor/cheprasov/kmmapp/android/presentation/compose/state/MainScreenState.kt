package yegor.cheprasov.kmmapp.android.presentation.compose.state

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import yegor.cheprasov.kmmapp.data.entities.GamePreview

sealed class MainScreenState {
    class Success(
        val gameList: Flow<PagingData<GamePreview>>
    ): MainScreenState()

    object Loading: MainScreenState()

    object ErrorL: MainScreenState()
}
