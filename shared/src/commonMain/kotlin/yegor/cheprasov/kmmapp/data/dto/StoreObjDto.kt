package yegor.cheprasov.kmmapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreObjDto(
    val count: Int,
    val results: List<StoreDto>
)

@Serializable
data class StoreDto(
    val id: Int,
    @SerialName("game_id")
    val gameId: String,
    @SerialName("store_id")
    val storeId: String,
    val url: String
)