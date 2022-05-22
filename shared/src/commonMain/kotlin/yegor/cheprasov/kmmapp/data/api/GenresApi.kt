package yegor.cheprasov.kmmapp.data.api

import io.ktor.client.call.*
import io.ktor.client.request.*
import yegor.cheprasov.kmmapp.data.Ktor
import yegor.cheprasov.kmmapp.data.dto.GenresDto

class GenresApi(private val ktor: Ktor) {

    suspend fun getListOfGenres(): GenresDto =
        ktor.httpClient.get("https://api.rawg.io/api/genres?key=${Ktor.API_KEY}").body()

}