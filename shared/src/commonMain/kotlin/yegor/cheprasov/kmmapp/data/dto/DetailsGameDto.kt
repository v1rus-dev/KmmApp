package yegor.cheprasov.kmmapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsGameDto(
    val id: Int,
    val slug: String,
    val name: String,
    @SerialName("name_original")
    val nameOriginal: String,
    val description: String,
    val metacritic: Int?,
    val released: String?,
    val website: String?,
    val rating: Float?,
    @SerialName("screenshots_count")
    val screenshotsCount: Int?,
    val platforms: List<PlatformsObjDto>
)