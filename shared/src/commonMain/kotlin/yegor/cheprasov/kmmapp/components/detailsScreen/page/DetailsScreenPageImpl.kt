package yegor.cheprasov.kmmapp.components.detailsScreen.page

import yegor.cheprasov.kmmapp.components.detailsScreen.description.ComponentDescription
import yegor.cheprasov.kmmapp.components.detailsScreen.description.ComponentDescriptionImpl
import yegor.cheprasov.kmmapp.components.detailsScreen.top.ComponentTop
import yegor.cheprasov.kmmapp.components.detailsScreen.top.ComponentTopImpl

class DetailsScreenPageImpl : DetailsScreenPage {

    override val top: ComponentTop = ComponentTopImpl("")
    override val description: ComponentDescription = ComponentDescriptionImpl("")
}