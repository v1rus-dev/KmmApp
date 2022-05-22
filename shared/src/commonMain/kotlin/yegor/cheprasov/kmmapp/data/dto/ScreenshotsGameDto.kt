package yegor.cheprasov.kmmapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotsGameObjDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ScreenshotsGameDto>
)

@Serializable
data class ScreenshotsGameDto(
    val id: String,
    @SerialName("image")
    val imageUrl: String
)