package yegor.cheprasov.kmmapp.android.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGame.state.DetailsGameState
import yegor.cheprasov.kmmapp.data.GamesRepository
import yegor.cheprasov.kmmapp.enitities.GameDetailsEntities

class DetailsViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    private val mutableState = MutableStateFlow(generateInitDetailsGameState())

    var canDownload = true
        private set

    val state: StateFlow<DetailsGameState>
        get() = mutableState

    private fun generateInitDetailsGameState() =
        DetailsGameState(
            id = 0,
            name = null,
            nameOriginal = "",
            description = "",
            metacritic = null,
            website = null,
            contentsElements = listOf(),
            rating = null,
            screenshotsCount = null,
            platforms = listOf(),
            screenshots = listOf(),
            stores = listOf()
        )

    fun downloadAllDetailsByGameId(gameId: String) = viewModelScope.launch(Dispatchers.IO) {
        val gameDetailsEntities = gamesRepository.getDetailsGame(gameId)
        canDownload = false
        mutableState.emit(gameDetailsEntities.mapToState())
        val stores = gamesRepository.getStores(gameId)
        mutableState.emit(state.value.copy(
            stores = stores
        ))
    }
}

fun GameDetailsEntities.mapToState(): DetailsGameState =
    DetailsGameState(
        id,
        name,
        nameOriginal,
        description,
        metacritic,
        website,
        contentsElements,
        rating,
        screenshotsCount,
        platforms,
        screenshots,
        listOf()
    )