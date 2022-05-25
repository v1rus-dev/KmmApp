package yegor.cheprasov.kmmapp.components.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import yegor.cheprasov.kmmapp.components.detailsScreen.page.DetailsScreenPage

interface Root {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class Page(val component: DetailsScreenPage) : Child()
    }
}