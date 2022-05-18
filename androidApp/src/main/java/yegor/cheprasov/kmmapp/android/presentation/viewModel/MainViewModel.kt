package yegor.cheprasov.kmmapp.android.presentation.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import yegor.cheprasov.kmmapp.android.presentation.compose.state.MainScreenState
import yegor.cheprasov.kmmapp.GameSource

class MainViewModel(private val gameSource: GameSource) : ViewModel() {

    private val refreshStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val mainStateFlow: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Loading)

    init {
        refresh()
        getGames()
    }


    private var isRefresh = false

    val mainState: StateFlow<MainScreenState>
        get() = mainStateFlow

    val refreshState: StateFlow<Boolean>
        get() = refreshStateFlow

    fun refresh() = viewModelScope.launch(Dispatchers.IO) {
        enableRefresh()
        gameSource.updateList()
    }

    fun downloadNextPage() = viewModelScope.launch(Dispatchers.IO) {
        if (!isRefresh) {
            gameSource.load()
        }
    }

    private fun getGames() = viewModelScope.launch(Dispatchers.IO) {
        gameSource.observeResult.collectLatest {
            mainStateFlow.emit(MainScreenState.Success(it))
            disableRefresh()
        }
    }

    private suspend fun enableRefresh() {
        refreshStateFlow.emit(true)
        isRefresh = true
    }

    private suspend fun disableRefresh() {
        refreshStateFlow.emit(false)
        isRefresh = false
    }
}