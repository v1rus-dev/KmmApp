package yegor.cheprasov.kmmapp.components.detailsScreen.top

import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable

interface ComponentTop {

    val models: Value<Model>

    fun updateReleasedDate(releasedDate: String)

    data class Model(
        val releasedDate: String
    ): Parcelable
}