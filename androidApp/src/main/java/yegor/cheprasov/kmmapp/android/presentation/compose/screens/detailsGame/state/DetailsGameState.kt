package yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGame.state

import yegor.cheprasov.kmmapp.data.dto.PlatformsObjDto
import yegor.cheprasov.kmmapp.enitities.ContentElementsEntities
import yegor.cheprasov.kmmapp.enitities.StoreEntities

data class DetailsGameState(
    val id: Int,
    val name: String?,
    val nameOriginal: String,
    val description: String,
    val metacritic: Int?,
    val website: String?,
    val contentsElements: List<ContentElementsEntities>,
    val rating: Float?,
    val screenshotsCount: Int?,
    val platforms: List<PlatformsObjDto>,
    val screenshots: List<String>,
    var stores: List<StoreEntities>
)