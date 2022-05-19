package yegor.cheprasov.kmmapp

import yegor.cheprasov.kmmapp.data.entities.GamePreviewDto
import yegor.cheprasov.kmmapp.data.entities.PlatformsObjDto
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