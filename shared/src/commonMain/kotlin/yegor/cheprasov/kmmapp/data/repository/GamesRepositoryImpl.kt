package yegor.cheprasov.kmmapp.data.repository

import yegor.cheprasov.kmmapp.data.GamesRepository
import yegor.cheprasov.kmmapp.data.api.GamesApi
import yegor.cheprasov.kmmapp.data.entities.GameListResultDto

class GamesRepositoryImpl(
    private val gamesApi: GamesApi
): GamesRepository {

    override suspend fun getGames(nextPageNumber: Int): GameListResultDto =
        gamesApi.getGames(nextPageNumber)

}