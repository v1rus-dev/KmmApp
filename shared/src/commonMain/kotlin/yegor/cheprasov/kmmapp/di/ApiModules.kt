package yegor.cheprasov.kmmapp.di

import org.koin.dsl.module
import yegor.cheprasov.kmmapp.data.GamesRepository
import yegor.cheprasov.kmmapp.data.Ktor
import yegor.cheprasov.kmmapp.data.api.GamesApi
import yegor.cheprasov.kmmapp.data.repository.GamesRepositoryImpl

val ktorModule = module {
    single { Ktor() }
}

val gamesApiModule = module {
    single { GamesApi(get()) }
}
val gamesRepositoryModule = module {
    single<GamesRepository> { GamesRepositoryImpl(get()) }
}