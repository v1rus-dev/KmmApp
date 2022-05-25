package yegor.cheprasov.kmmapp.components.detailsScreen.description

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce

class ComponentDescriptionImpl(
    private val description: String
) : ComponentDescription {

    private val _models = MutableValue(ComponentDescription.Model(description))

    override val models: Value<ComponentDescription.Model>
        get() = _models

    override fun updateDescription(description: String) {
        _models.reduce { it.copy(description = description) }
    }
}