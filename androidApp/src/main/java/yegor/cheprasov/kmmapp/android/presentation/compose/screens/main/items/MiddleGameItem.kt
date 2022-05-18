package yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.getFakeGameList
import yegor.cheprasov.kmmapp.data.entities.GamePreview

@Composable
fun MiddleGameItem(
    gamePreview: GamePreview
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(184.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 10.dp
    ) {
        gamePreview.backgroundImage?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp, start = 16.dp),
                text = gamePreview.name,
                fontFamily = FontFamily.Monospace,
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Preview
@Composable
private fun PreviewMiddleGameItem() {
    MiddleGameItem(gamePreview = getFakeGameList()[0])
}