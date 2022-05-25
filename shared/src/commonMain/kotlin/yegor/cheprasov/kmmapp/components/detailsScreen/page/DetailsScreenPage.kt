package yegor.cheprasov.kmmapp.components.detailsScreen.page

import yegor.cheprasov.kmmapp.components.detailsScreen.description.ComponentDescription
import yegor.cheprasov.kmmapp.components.detailsScreen.top.ComponentTop

interface DetailsScreenPage {
    val description: ComponentDescription
    val top: ComponentTop
}