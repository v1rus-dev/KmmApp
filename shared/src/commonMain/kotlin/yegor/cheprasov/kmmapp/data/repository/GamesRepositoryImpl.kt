package yegor.cheprasov.kmmapp.data.repository

import yegor.cheprasov.kmmapp.data.GamesRepository
import yegor.cheprasov.kmmapp.data.api.GamesApi
import yegor.cheprasov.kmmapp.data.entities.GameListResult

class GamesRepositoryImpl(
    private val gamesApi: GamesApi
): GamesRepository {

    override suspend fun getGames(nextPageNumber: Int): GameListResult =
        gamesApi.getGames(nextPageNumber)

}