package yegor.cheprasov.kmmapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import yegor.cheprasov.kmmapp.extentions.getUrlParam
import yegor.cheprasov.kmmapp.data.GamesRepository
import yegor.cheprasov.kmmapp.enitities.GamePreviewEntity

class GameSource(
    private val gamesRepository: GamesRepository
) {

    private var nextPage: Int = 1

    private var list = arrayListOf<GamePreviewEntity>()

    private val mutableResult = MutableSharedFlow<List<GamePreviewEntity>>()

    val observeResult: Flow<List<GamePreviewEntity>>
        get() = mutableResult

    suspend fun load() {
        val gameListResult = gamesRepository.getGames(nextPage)
        nextPage = getNextKey(gameListResult.next)
        list.addAll(gameListResult.results.mapToGamePreviewList())
        mutableResult.emit(list)
    }

    suspend fun updateList() {
        nextPage = 1
        val gameListResult = gamesRepository.getGames(nextPage)
        list = gameListResult.results.mapToGamePreviewList() as ArrayList<GamePreviewEntity>
        mutableResult.emit(list)
    }

    private fun getNextKey(next: String?): Int = next?.getUrlParam("page")?.toInt() ?: 1


}