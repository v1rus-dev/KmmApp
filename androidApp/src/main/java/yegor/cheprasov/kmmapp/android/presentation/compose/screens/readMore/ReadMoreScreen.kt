package yegor.cheprasov.kmmapp.android.presentation.compose.screens.readMore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.detailsGame.fake.getSuperLongText

@Composable
fun ReadMoreScreen() {
    val scrollState = rememberScrollState()
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
        ) {
            Text(
                text = getSuperLongText(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }
    }
}