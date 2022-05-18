package yegor.cheprasov.kmmapp.android.presentation.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import yegor.cheprasov.kmmapp.data.api.GamesApi
import yegor.cheprasov.kmmapp.data.entities.GameListResult

class MainViewModel(private val gamesApi: GamesApi) : ViewModel() {

    private val mutableGames = MutableSharedFlow<GameListResult>()

    val games: LiveData<GameListResult>
        get() = mutableGames.asLiveData()

    fun getGames() = viewModelScope.launch(Dispatchers.IO) {
        val games = gamesApi.getGames()
        mutableGames.emit(games)
    }

}