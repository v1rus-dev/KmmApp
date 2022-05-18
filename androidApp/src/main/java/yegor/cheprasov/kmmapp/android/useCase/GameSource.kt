package yegor.cheprasov.kmmapp.android.useCase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import yegor.cheprasov.kmmapp.android.getUrlParam
import yegor.cheprasov.kmmapp.data.GamesRepository
import yegor.cheprasov.kmmapp.data.entities.GamePreview

class GameSource(
    private val gamesRepository: GamesRepository
) : PagingSource<Int, GamePreview>() {

    override fun getRefreshKey(state: PagingState<Int, GamePreview>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GamePreview> {
        val nextPage = params.key ?: 1
        val gamesListResult = gamesRepository.getGames(nextPage)
        val getNext = getNextKey(gamesListResult.next)
        val getPrev = if (gamesListResult.previous == null) {
            null
        } else {
            getPrevKey(gamesListResult.previous!!)
        }
        return LoadResult.Page(
            data = gamesListResult.results,
            prevKey = getPrev,
            nextKey = getNext
        )
    }

    private fun getPrevKey(prev: String): Int = prev.getUrlParam("page")?.toInt() ?: 1

    private fun getNextKey(next: String?): Int = next?.getUrlParam("page")?.toInt() ?: 1


}