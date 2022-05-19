package yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.items

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import yegor.cheprasov.kmmapp.android.GamePreview
import yegor.cheprasov.kmmapp.android.Platform
import yegor.cheprasov.kmmapp.android.presentation.compose.components.ShimLoadingEffect
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.fake.getFakeGameList

@Composable
fun MiniGameItem(
    gamePreview: GamePreview
) {
    Card(
        modifier = Modifier.size(163.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            gamePreview.backgroundImage?.let {
                SubcomposeAsyncImage(
                    model = it,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading) {
                        ShimLoadingEffect(163.dp)
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xD6000000)
                            )
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        gamePreview.platforms.toSet().forEach {
                            if (it !is Platform.Other || it !is Platform.NintendoSwitch) {
                                it.imageRes?.let { imageRes ->
                                    Image(
                                        painter = painterResource(id = imageRes),
                                        contentDescription = null
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(7.dp))
                        }
                    }
                    gamePreview.metacritic?.let {
                        Column(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .border(
                                    1.dp,
                                    color = Color(0xFF00C82C),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .size(20.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = it.toString(),
                                color = Color(0xFF00C82C),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp, start = 16.dp)
                        .width(280.dp),
                    text = gamePreview.name,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 22.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMiniGameItem() {
    MiniGameItem(gamePreview = getFakeGameList()[0])
}