package yegor.cheprasov.kmmapp.android.presentation.compose.fake

import androidx.paging.PagingData
import kotlinx.coroutines.flow.flowOf
import yegor.cheprasov.kmmapp.android.presentation.compose.state.MainScreenState
import yegor.cheprasov.kmmapp.data.entities.GamePreview
import yegor.cheprasov.kmmapp.data.entities.Platform
import yegor.cheprasov.kmmapp.data.entities.PlatformsObj

fun getMainFakeScreenSuccess() = MainScreenState.Success(
    gameList = flowOf(PagingData.from(getFakeGameList()))
)

fun getFakeGameList() = listOf(
    GamePreview(
        id = 0,
        name = "Grand Theft Auto V",
        released = "2013-09-17",
        tba = false,
        backgroundImage = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
        rating = 4.48f,
        ratingTop = 5f,
        metacritic = 92,
        platforms = listOf(
            PlatformsObj(
                Platform(
                    id = 4,
                    slug = "PC",
                    name = "pc",
                    imageBackground = "https://media.rawg.io/media/games/511/5118aff5091cb3efec399c808f8c598f.jpg"
                ),
                releasedAt = "2013-09-17"
            )
        )
    ),
    GamePreview(
        id = 3328,
        name = "The Witcher 3: Wild Hunt",
        released = "2015-05-18",
        tba = false,
        backgroundImage = "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg",
        rating = 4.67f,
        ratingTop = 5f,
        metacritic = 92,
        platforms = listOf(
            PlatformsObj(
                Platform(
                    id = 187,
                    slug = "playstation5",
                    name = "PlayStation 5",
                    imageBackground = "https://media.rawg.io/media/games/560/56056a71c74f751552c9baedebf8f317.jpg"
                ),
                releasedAt = "2015-05-18"
            ),
            PlatformsObj(
                Platform(
                    id = 4,
                    slug = "PC",
                    name = "pc",
                    imageBackground = "https://media.rawg.io/media/games/511/5118aff5091cb3efec399c808f8c598f.jpg"
                ),
                releasedAt = "2015-05-18"
            )
        )
    )
)