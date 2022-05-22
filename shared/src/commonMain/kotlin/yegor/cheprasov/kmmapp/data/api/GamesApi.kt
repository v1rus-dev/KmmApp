package yegor.cheprasov.kmmapp.data.api

import io.ktor.client.call.*
import io.ktor.client.request.*
import yegor.cheprasov.kmmapp.data.Ktor
import yegor.cheprasov.kmmapp.data.dto.DetailsGameDto
import yegor.cheprasov.kmmapp.data.dto.GameListResultDto
import yegor.cheprasov.kmmapp.data.dto.ScreenshotsGameObjDto
import yegor.cheprasov.kmmapp.data.dto.StoreObjDto

class GamesApi(private val ktor: Ktor) {

    companion object {
        const val PAGE_SIZE = 20
    }

    suspend fun getGames(nextPageNumber: Int = 1): GameListResultDto =
        ktor.httpClient.get("https://api.rawg.io/api/games?key=${Ktor.API_KEY}&page=$nextPageNumber&page_size=$PAGE_SIZE").body()

    suspend fun getDetailsGame(gameId: String): DetailsGameDto =
        ktor.httpClient.get("https://api.rawg.io/api/games/$gameId?key=${Ktor.API_KEY}").body()

    suspend fun getScreenshotsForGame(gameId: String): ScreenshotsGameObjDto =
        ktor.httpClient.get("https://api.rawg.io/api/games/$gameId/screenshots?key=${Ktor.API_KEY}").body()

    suspend fun getStoreWhereGameSell(gameId: String): StoreObjDto =
        ktor.httpClient.get("https://api.rawg.io/api/games/$gameId/stores?key=${Ktor.API_KEY}").body()

}