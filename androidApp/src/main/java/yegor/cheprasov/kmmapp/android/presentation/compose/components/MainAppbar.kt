package yegor.cheprasov.kmmapp.android.presentation.compose.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import yegor.cheprasov.kmmapp.android.R
import yegor.cheprasov.kmmapp.android.utils.ViewType

@Composable
fun MainAppbar(
    modifier: Modifier = Modifier,
    scrollUpState: State<Boolean?>,
    text: String,
    onClickFilter: () -> Unit,
    viewType: ViewType,
    onClickViewType: () -> Unit,
    onTextChanged: (String) -> Unit
) {
    val position by animateFloatAsState(if (scrollUpState.value == true) -450f else 0f)
    Column(
        modifier = Modifier
            .graphicsLayer { translationY = (position) }
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .height(52.dp)
                .border(width = 1.dp, color = Color(0xFFD4D4D4), shape = RoundedCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier.padding(start = 20.dp),
                painter = painterResource(id = R.drawable.ic_search), contentDescription = null
            )
            TextField(
                modifier = Modifier.padding(start = 9.dp),
                value = text,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    textColor = Color.Black
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Search",
                        color = Color(0xFFD4D4D4),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                onValueChange = { onTextChanged(it) }
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.padding(start = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp
                ),
                onClick = { onClickFilter() }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 14.sp,
                        text = "Filters"
                    )
                }
            }
            Button(
                modifier = Modifier.padding(end = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp
                ),
                onClick = { onClickViewType() }) {
                    Icon(
                        painter = painterResource(id = when(viewType) {
                            ViewType.MINI -> R.drawable.ic_view_type_lines
                            ViewType.MIDDLE -> R.drawable.ic_view_type
                        }),
                        contentDescription = null
                    )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainAppbar() {
    MainAppbar(
        scrollUpState = MutableStateFlow(false).collectAsState(),
        onTextChanged = {},
        text = "",
        viewType = ViewType.MIDDLE,
        onClickFilter = {},
        onClickViewType = {}
    )
}