package yegor.cheprasov.kmmapp.data.api

import io.ktor.client.call.*
import io.ktor.client.request.*
import yegor.cheprasov.kmmapp.data.Ktor
import yegor.cheprasov.kmmapp.data.entities.GameListResult

class GamesApi(private val ktor: Ktor) {

    suspend fun getGames(nextPageNumber: Int = 1): GameListResult =
        ktor.httpClient.get("https://api.rawg.io/api/games?key=${Ktor.API_KEY}&page=$nextPageNumber&page_size=50").body()

}