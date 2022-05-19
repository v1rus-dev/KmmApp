package yegor.cheprasov.kmmapp.android.presentation.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import yegor.cheprasov.kmmapp.android.GamePreview
import yegor.cheprasov.kmmapp.android.presentation.compose.state.MainScreenState
import yegor.cheprasov.kmmapp.GameSource
import yegor.cheprasov.kmmapp.android.Platform
import yegor.cheprasov.kmmapp.android.utils.ViewType
import yegor.cheprasov.kmmapp.enitities.GamePreviewEntity
import yegor.cheprasov.kmmapp.extentions.containsBy
import yegor.cheprasov.kmmapp.extentions.setByWithList

class MainViewModel(private val gameSource: GameSource) : ViewModel() {

    private var lastScrollPosition = 0

    private val refreshStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val mainStateFlow: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Loading)

    private val scrollUpMutable = MutableStateFlow(false)
    val scrollUp: StateFlow<Boolean>
        get() = scrollUpMutable

    private val mutableViewType = MutableStateFlow(ViewType.MINI)
    val viewType: StateFlow<ViewType>
        get() = mutableViewType

    init {
        refresh()
        getGames()
    }


    private var isRefresh = false
    private var isLoading = false

    val mainState: StateFlow<MainScreenState>
        get() = mainStateFlow

    val refreshState: StateFlow<Boolean>
        get() = refreshStateFlow

    fun refresh() = viewModelScope.launch(Dispatchers.IO) {
        enableRefresh()
        gameSource.updateList()
    }

    fun updateScrollPosition(newScrollPosition: Int) = viewModelScope.launch {
        if (newScrollPosition != lastScrollPosition) {
            scrollUpMutable.emit(newScrollPosition > lastScrollPosition)
            lastScrollPosition = newScrollPosition
        }
    }

    fun downloadNextPage() = viewModelScope.launch(Dispatchers.IO) {
        if (!isRefresh && !isLoading) {
            isLoading = true
            gameSource.load()
            isLoading = false
        }
    }

    fun changeViewType() = viewModelScope.launch {
        mutableViewType.emit(
            if (viewType.value == ViewType.MIDDLE) {
                ViewType.MINI
            } else {
                ViewType.MIDDLE
            }
        )
    }

    private fun getGames() = viewModelScope.launch(Dispatchers.IO) {
        gameSource.observeResult.collectLatest { list ->
            mainStateFlow.emit(MainScreenState.Success(list.map { it.toGamePreview() }))
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

    private fun GamePreviewEntity.toGamePreview(): GamePreview =
        GamePreview(
            id = this.id,
            name = this.name,
            released = this.released,
            backgroundImage = this.backgroundImage,
            metacritic = this.metacritic,
            platforms = this.platforms.map { Platform.getPlatformFromPlatformType(it) }.sortedBy { it.id }.setByWithList { list, platform ->
                !list.containsBy { it.id == platform.id }
            }
        )
}