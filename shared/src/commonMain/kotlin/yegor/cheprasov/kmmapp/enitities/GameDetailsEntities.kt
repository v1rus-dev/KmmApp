package yegor.cheprasov.kmmapp.enitities

import yegor.cheprasov.kmmapp.data.dto.PlatformsObjDto

data class GameDetailsEntities(
    val id: Int,
    val name: String,
    val nameOriginal: String,
    val description: String,
    val metacritic: Int?,
    val website: String?,
    val contentsElements: List<ContentElementsEntities>,
    val rating: Float?,
    val screenshotsCount: Int?,
    val platforms: List<PlatformsObjDto>,
    val screenshots: List<String>
)