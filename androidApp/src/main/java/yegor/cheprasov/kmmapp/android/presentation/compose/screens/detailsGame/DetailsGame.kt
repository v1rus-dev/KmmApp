package yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGame

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import org.koin.androidx.compose.getViewModel
import yegor.cheprasov.kmmapp.android.presentation.compose.components.ShimLoadingEffect
import yegor.cheprasov.kmmapp.android.presentation.compose.navigation.navigateToReadMore
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGame.fake.getDetailsGameFake
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGame.state.DetailsGameState
import yegor.cheprasov.kmmapp.android.presentation.viewModel.DetailsViewModel
import yegor.cheprasov.kmmapp.android.utils.toAnnotatedString

@Composable
fun DetailsGame(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = getViewModel(),
    gameId: String,
    gameName: String,
    imageUrl: String?
) {
    if (detailsViewModel.canDownload) {
        detailsViewModel.downloadAllDetailsByGameId(gameId)
    }
    val screenState = detailsViewModel.state.collectAsState()
    DetailsGame(
        imageUrl = imageUrl,
        gameName = gameName,
        screenState = screenState.value
    ) { action ->
        when (action) {
            is DetailsGameAction.ReadMore -> navController.navigateToReadMore()
        }
    }
}

@Composable
fun DetailsGame(
    imageUrl: String?,
    gameName: String,
    screenState: DetailsGameState,
    onAction: (DetailsGameAction) -> Unit
) {
    Scaffold() {
        Box {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(318.dp),
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading) {
                    ShimLoadingEffect(184.dp)
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
            MainContentGame(
                gameName = gameName,
                gameDetails = screenState,
                onAction = onAction
            )
        }
    }
}

@Composable
fun MainContentGame(
    gameName: String,
    gameDetails: DetailsGameState,
    onAction: (DetailsGameAction) -> Unit
) {
    val verticalScroll = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(verticalScroll)
    ) {
        Spacer(modifier = Modifier.size(270.dp))
        Card(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .border(
                            BorderStroke(1.dp, Color.White),
                            RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        ),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 16.dp),
                        text = gameDetails.name ?: gameName,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
                MainContentElements(
                    modifier = Modifier.padding(vertical = 20.dp),
                    gameDetails = gameDetails
                )
                Description(
                    description = gameDetails.description,
                    onAction = onAction
                )
            }
        }
    }
}

@Composable
fun MainContentElements(
    modifier: Modifier = Modifier,
    gameDetails: DetailsGameState
) {
    Column(
        modifier = modifier
    ) {
        gameDetails.contentsElements.forEachIndexed { index, contentElements ->
            Row(
                modifier = Modifier
                    .height(18.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = contentElements.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFC5C6C7)
                )
                Text(
                    text = contentElements.value,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
            if (index != gameDetails.contentsElements.lastIndex) {
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun Description(
    description: String,
    onAction: (DetailsGameAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Description",
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.size(16.dp))
        if (description.isNotEmpty()) {
            Text(
                text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
                    .toAnnotatedString(),
                fontSize = 15.sp,
                maxLines = 8,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF3F3D3D),
                lineHeight = 16.sp
            )
            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable(role = Role.Button) {
                        onAction(
                            DetailsGameAction.ReadMore(description)
                        )
                    },
                text = "Read more",
                color = Color(0xFF3093FF),
                fontSize = 15.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewMainContentGame() {
    MainContentGame(
        gameName = "Vampire: The Masguerade -\n Bloodlines 2",
        gameDetails = getDetailsGameFake(),
        onAction = {}
    )
}

@Preview
@Composable
private fun PreviewDetailsGame() {

}