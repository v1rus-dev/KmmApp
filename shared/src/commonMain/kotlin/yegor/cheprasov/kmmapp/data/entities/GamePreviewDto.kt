package yegor.cheprasov.kmmapp.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameListResultDto(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<GamePreviewDto>
)

@Serializable
data class GamePreviewDto(
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
    val platforms: List<PlatformsObjDto>
)

@Serializable
data class PlatformsObjDto(
    val platform: PlatformDto,
    @SerialName("released_at")
    val releasedAt: String?
)

@Serializable
data class PlatformDto(
    val id: Int,
    val slug: String,
    val name: String,
    @SerialName("image_background")
    val imageBackground: String
)