package yegor.cheprasov.kmmapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import yegor.cheprasov.kmmapp.android.getUrlParam
import yegor.cheprasov.kmmapp.data.GamesRepository
import yegor.cheprasov.kmmapp.data.entities.GamePreview

class GameSource(
    private val gamesRepository: GamesRepository
) {

    private var nextPage: Int = 1

    private var list = arrayListOf<GamePreview>()

    private val mutableResult = MutableSharedFlow<List<GamePreview>>()

    val observeResult: Flow<List<GamePreview>>
        get() = mutableResult

    suspend fun load() {
        val gameListResult = gamesRepository.getGames(nextPage)
        nextPage = getNextKey(gameListResult.next)
        list.addAll(gameListResult.results)
        mutableResult.emit(list)
    }

    suspend fun updateList() {
        nextPage = 1
        val gameListResult = gamesRepository.getGames(nextPage)
        list = gameListResult.results as ArrayList<GamePreview>
        mutableResult.emit(list)
    }

    private fun getNextKey(next: String?): Int = next?.getUrlParam("page")?.toInt() ?: 1


}