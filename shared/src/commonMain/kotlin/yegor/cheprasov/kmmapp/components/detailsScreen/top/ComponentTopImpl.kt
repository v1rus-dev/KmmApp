package yegor.cheprasov.kmmapp.components.detailsScreen.top

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce

class ComponentTopImpl(
    private val releasedDate: String
) : ComponentTop {

    private val _models = MutableValue(ComponentTop.Model(releasedDate))

    override val models: Value<ComponentTop.Model>
        get() = _models

    override fun updateReleasedDate(releasedDate: String) {
        _models.reduce { it.copy(releasedDate = releasedDate) }
    }
}
