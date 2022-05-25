package yegor.cheprasov.kmmapp.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import yegor.cheprasov.kmmapp.components.detailsScreen.page.DetailsScreenPageImpl

class RootImpl(componentContext: ComponentContext) : Root, ComponentContext by componentContext {

    private val router = router(Config.Page(index = 0), childFactory = ::child)

    private fun child(config: Config, componentContext: ComponentContext): Root.Child =
        when (config) {
            is Config.Page -> Root.Child.Page(DetailsScreenPageImpl())
        }

    override val routerState: Value<RouterState<*, Root.Child>>
        get() = router.state

    private sealed class Config : Parcelable {
        data class Page(val index: Int) : Config()
    }
}