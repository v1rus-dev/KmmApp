package yegor.cheprasov.kmmapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Genre>
)

@Serializable
data class Genre(
    val id: Int,
    val name: String,
    @SerialName("games_count")
    val gamesCount: Int
)