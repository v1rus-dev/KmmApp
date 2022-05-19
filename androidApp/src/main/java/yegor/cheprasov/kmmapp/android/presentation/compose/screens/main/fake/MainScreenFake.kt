package yegor.cheprasov.kmmapp.android.presentation.compose.screens.main.fake

import yegor.cheprasov.kmmapp.android.GamePreview
import yegor.cheprasov.kmmapp.android.Platform
import yegor.cheprasov.kmmapp.android.presentation.compose.state.MainScreenState

fun getMainFakeScreenSuccess() = MainScreenState.Success(
    gameList = getFakeGameList()
)

fun getFakeGameList() = listOf(
    GamePreview(
        id = 0,
        name = "Grand Theft Auto V",
        released = "2013-09-17",
        backgroundImage = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
        metacritic = 92,
        platforms = listOf(
            Platform.PC("2013-09-17")
        )
    ),
    GamePreview(
        id = 3328,
        name = "The Witcher 3: Wild Hunt",
        released = "2015-05-18",
        backgroundImage = "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg",
        metacritic = 92,
        platforms = listOf(
            Platform.Playstation("2015-05-18"),
            Platform.PC("2015-05-18")
        )
    )
)