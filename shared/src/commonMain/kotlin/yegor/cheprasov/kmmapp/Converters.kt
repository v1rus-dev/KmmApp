package yegor.cheprasov.kmmapp

import yegor.cheprasov.kmmapp.data.dto.DetailsGameDto
import yegor.cheprasov.kmmapp.data.dto.GamePreviewDto
import yegor.cheprasov.kmmapp.data.dto.PlatformsObjDto
import yegor.cheprasov.kmmapp.data.dto.ScreenshotsGameObjDto
import yegor.cheprasov.kmmapp.enitities.ContentElementsEntities
import yegor.cheprasov.kmmapp.enitities.GameDetailsEntities
import yegor.cheprasov.kmmapp.enitities.GamePreviewEntity
import yegor.cheprasov.kmmapp.enitities.PlatformType

fun List<GamePreviewDto>.mapToGamePreviewList(): List<GamePreviewEntity> =
    this.map { it.mapToGamePreview() }

fun GamePreviewDto.mapToGamePreview(): GamePreviewEntity =
    GamePreviewEntity(
        id = this.id,
        name = this.name,
        released = this.released,
        backgroundImage = this.backgroundImage,
        metacritic = this.metacritic,
        platforms = this.platforms.map { it.mapToPlatform() }
    )

fun PlatformsObjDto.mapToPlatform(): PlatformType =
    when(this.platform.slug) {
        "pc" -> PlatformType.PC(this.releasedAt)
        "playstation4" -> PlatformType.Playstation4(this.releasedAt)
        "playstation5" -> PlatformType.Playstation5(this.releasedAt)
        "nintendoswitch" -> PlatformType.NintendoSwitch(this.releasedAt)
        "xbox-series-x" -> PlatformType.Xbox(this.releasedAt)
        else -> PlatformType.Other(this.releasedAt)
    }

fun DetailsGameDto.mapToPreview(screenshotsGameDto: ScreenshotsGameObjDto): GameDetailsEntities =
    GameDetailsEntities(
        id = this.id,
        name = this.name,
        nameOriginal = nameOriginal,
        description = description,
        metacritic = metacritic,
        contentsElements = getContentElements(this),
        website = website,
        rating = rating,
        screenshotsCount = screenshotsCount,
        platforms = platforms,
        screenshots = screenshotsGameDto.results.map { it.imageUrl }
    )

private fun getContentElements(detailsGameDto: DetailsGameDto): List<ContentElementsEntities> {
    val result = arrayListOf<ContentElementsEntities>()
    if (detailsGameDto.released != null && detailsGameDto.released.isNotEmpty()) {
        result.add(
            ContentElementsEntities(
                title = "Release date",
                value = detailsGameDto.released
            )
        )
    }
    return result.toList()
}