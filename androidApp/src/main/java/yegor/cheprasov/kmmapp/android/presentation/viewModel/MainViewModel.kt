package yegor.cheprasov.kmmapp.android.presentation.viewModel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import yegor.cheprasov.kmmapp.android.presentation.compose.state.MainScreenState
import yegor.cheprasov.kmmapp.android.useCase.GameSource

class MainViewModel(private val gameSource: GameSource) : ViewModel() {

    private val refreshStateFlow: MutableStateFlow<Boolean>
    private val mainStateFlow: MutableStateFlow<MainScreenState>

    init {
        refreshStateFlow = MutableStateFlow(false)
        mainStateFlow = MutableStateFlow(MainScreenState.Loading)
        getGames()
    }


    private var isRefresh = false

    val mainState: StateFlow<MainScreenState>
        get() = mainStateFlow

    val refreshState: StateFlow<Boolean>
        get() = refreshStateFlow

    fun refresh() {
        getGames()
    }

    private fun getGames() = viewModelScope.launch(Dispatchers.IO) {
        if (!isRefresh) {
            enableRefresh()
            mainStateFlow.emit(MainScreenState.Success(Pager(PagingConfig(50)) { gameSource }.flow))
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