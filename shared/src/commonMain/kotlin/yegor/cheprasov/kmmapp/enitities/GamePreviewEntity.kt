package yegor.cheprasov.kmmapp.enitities

data class GamePreviewEntity(
    val id: Int,
    val name: String,
    val released: String?,
    val backgroundImage: String?,
    val metacritic: Int?,
    val platforms: List<PlatformType>
)