package yegor.cheprasov.kmmapp.data

import yegor.cheprasov.kmmapp.data.entities.GameListResult

interface GamesRepository {

    suspend fun getGames(nextPageNumber: Int = 1): GameListResult

}