package yegor.cheprasov.kmmapp.components.detailsScreen.description

import com.arkivanov.decompose.value.Value

interface ComponentDescription {

    val models: Value<Model>

    fun updateDescription(description: String)

    data class Model(
        val description: String
    )
}