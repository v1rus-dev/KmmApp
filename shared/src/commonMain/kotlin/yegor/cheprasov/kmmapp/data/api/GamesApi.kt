package yegor.cheprasov.kmmapp.data.api

import io.ktor.client.call.*
import io.ktor.client.request.*
import yegor.cheprasov.kmmapp.data.Ktor
import yegor.cheprasov.kmmapp.data.entities.GameListResultDto

class GamesApi(private val ktor: Ktor) {

    companion object {
        const val PAGE_SIZE = 20
    }

    suspend fun getGames(nextPageNumber: Int = 1): GameListResultDto =
        ktor.httpClient.get("https://api.rawg.io/api/games?key=${Ktor.API_KEY}&page=$nextPageNumber&page_size=$PAGE_SIZE").body()

}