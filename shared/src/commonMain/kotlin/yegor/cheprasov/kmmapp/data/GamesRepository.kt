package yegor.cheprasov.kmmapp.data

import yegor.cheprasov.kmmapp.data.dto.GameListResultDto
import yegor.cheprasov.kmmapp.enitities.GameDetailsEntities
import yegor.cheprasov.kmmapp.enitities.StoreEntities

interface GamesRepository {

    suspend fun getGames(nextPageNumber: Int = 1): GameListResultDto

    suspend fun getDetailsGame(gameId: String): GameDetailsEntities

    suspend fun getStores(gameId: String): List<StoreEntities>
}