package yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGame

sealed class DetailsGameAction {
    class ReadMore(val text: String): DetailsGameAction()
}
