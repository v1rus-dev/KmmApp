package yegor.cheprasov.kmmapp.android.presentation.compose.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import yegor.cheprasov.kmmapp.android.GamePreview
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.action.MainScreenAction
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.fake.getFakeGameList
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.items.MiddleGameItem
import yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.items.MiniGameItem
import yegor.cheprasov.kmmapp.android.utils.ViewType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuccessGameList(
    list: List<GamePreview>,
    isRefreshing: Boolean,
    viewType: ViewType,
    onAction: (MainScreenAction) -> Unit
) {
    val rememberSwipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    val scrollState = rememberLazyListState()
    onAction(MainScreenAction.ChangeScrollPosition(scrollState.firstVisibleItemIndex))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState,
            onRefresh = { onAction(MainScreenAction.Refresh) },
            indicatorPadding = PaddingValues(140.dp),
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    scale = true
                )
            }
        ) {
            when (viewType) {
                ViewType.MINI -> {
                    LazyVerticalGrid(
                        state = scrollState,
                        cells = GridCells.Fixed(2),
                        contentPadding = PaddingValues(top = 140.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(list.size) { index ->
                            list[index].let {
                                MiniGameItem(gamePreview = it)
                                if (index == list.lastIndex - 6) {
                                    onAction(MainScreenAction.LoadingNextPage)
                                }
                            }
                        }
                    }
                }
                ViewType.MIDDLE -> LazyColumn(
                    state = scrollState,
                    contentPadding = PaddingValues(top = 140.dp)
                ) {
                    items(list.size) { index ->
                        list[index].let {
                            MiddleGameItem(gamePreview = it)
                            Spacer(modifier = Modifier.size(16.dp))
                            if (index == list.lastIndex - 3) {
                                onAction(MainScreenAction.LoadingNextPage)
                            }
                        }
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewSuccessScreen() {
    SuccessGameList(list = getFakeGameList(), isRefreshing = false, viewType = ViewType.MINI) {}
}