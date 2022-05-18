package yegor.cheprasov.kmmapp.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameListResult(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<GamePreview>
)

@Serializable
data class GamePreview(
    val id: Int,
    val name: String,
    val released: String?,
    val tba: Boolean,
    @SerialName("background_image")
    val backgroundImage: String?,
    val rating: Float,
    @SerialName("rating_top")
    val ratingTop: Float,
    val metacritic: Int?,
    val platforms: List<PlatformsObj>
)

@Serializable
data class PlatformsObj(
    val platform: Platform,
    @SerialName("released_at")
    val releasedAt: String?
)

@Serializable
data class Platform(
    val id: Int,
    val slug: String,
    val name: String,
    @SerialName("image_background")
    val imageBackground: String
)