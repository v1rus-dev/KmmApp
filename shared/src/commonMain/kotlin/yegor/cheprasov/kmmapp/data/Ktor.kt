package yegor.cheprasov.kmmapp.data

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Ktor {

    companion object {
        const val API_KEY = "b23a23eb0c714541b4c5400c1c55e2f9"
    }

    var httpClient: HttpClient = HttpClient() {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }
        private set

}