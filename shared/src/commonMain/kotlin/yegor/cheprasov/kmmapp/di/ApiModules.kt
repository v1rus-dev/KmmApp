package yegor.cheprasov.kmmapp.di

import org.koin.dsl.module
import yegor.cheprasov.kmmapp.data.Ktor
import yegor.cheprasov.kmmapp.data.api.GamesApi

val ktorModule = module {
    single { Ktor() }
}

val gamesApiModule = module {
    single { GamesApi(get<Ktor>()) }
}