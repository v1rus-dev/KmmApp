package yegor.cheprasov.kmmapp.android.presentation.compose.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import yegor.cheprasov.kmmapp.android.presentation.compose.actions.MainScreenAction
import yegor.cheprasov.kmmapp.android.presentation.compose.fake.getFakeGameList
import yegor.cheprasov.kmmapp.data.entities.GamePreview

@Composable
fun SuccessGameList(
    list: LazyPagingItems<GamePreview>,
    isRefreshing: Boolean,
    onRefresh: (MainScreenAction) -> Unit
) {
    val rememberSwipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState,
            onRefresh = { onRefresh(MainScreenAction.Refresh) },
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    scale = true
                )
            }
        ) {
            LazyColumn() {
                items(list.itemCount) {index ->
                    list[index]?.let {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSuccessScreen() {
    SuccessGameList(list = flowOf(PagingData.from(getFakeGameList())).collectAsLazyPagingItems(), isRefreshing = false) {}
}