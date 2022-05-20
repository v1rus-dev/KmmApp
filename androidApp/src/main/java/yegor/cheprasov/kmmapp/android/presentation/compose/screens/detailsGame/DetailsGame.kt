package yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGame

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import org.koin.androidx.compose.getViewModel
import yegor.cheprasov.kmmapp.android.presentation.compose.components.ShimLoadingEffect
import yegor.cheprasov.kmmapp.android.presentation.viewModel.DetailsViewModel

@Composable
fun DetailsGame(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = getViewModel(),
    gameId: String,
    gameName: String?,
    imageUrl: String?
) {
    DetailsGame(imageUrl, gameName)
}

@Composable
fun DetailsGame(imageUrl: String?, gameName: String?) {
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
            MainContentGame(gameName)
        }
    }
}

@Composable
fun MainContentGame(gameName: String?) {
    val verticalScroll = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(verticalScroll)
            .background(Color.Transparent)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.size(270.dp))
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .border(
                    BorderStroke(1.dp, Color.White),
                    RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 16.dp),
                text = gameName ?: "",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun PreviewMainContentGame() {
    MainContentGame(gameName = "Vampire: The Masguerade -\n Bloodlines 2")
}

@Preview
@Composable
private fun PreviewDetailsGame() {

}