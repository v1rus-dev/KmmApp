package yegor.cheprasov.kmmapp.data.repository

import yegor.cheprasov.kmmapp.data.GamesRepository
import yegor.cheprasov.kmmapp.data.api.GamesApi
import yegor.cheprasov.kmmapp.data.dto.GameListResultDto
import yegor.cheprasov.kmmapp.enitities.GameDetailsEntities
import yegor.cheprasov.kmmapp.enitities.StoreEntities
import yegor.cheprasov.kmmapp.mapToPreview

class GamesRepositoryImpl(
    private val gamesApi: GamesApi
): GamesRepository {

    override suspend fun getGames(nextPageNumber: Int): GameListResultDto =
        gamesApi.getGames(nextPageNumber)

    override suspend fun getDetailsGame(gameId: String): GameDetailsEntities {
        val games = gamesApi.getDetailsGame(gameId)
        val screenshotsGameDto = gamesApi.getScreenshotsForGame(gameId)
        return games.mapToPreview(screenshotsGameDto)
    }

    override suspend fun getStores(gameId: String): List<StoreEntities> {
        val storeObj = gamesApi.getStoreWhereGameSell(gameId)
        return storeObj.results.map { storeDto ->
            StoreEntities(
                id = storeDto.id,
                gameId = storeDto.gameId,
                storeId = storeDto.storeId,
                url = storeDto.url
            )
        }
    }
}