package yegor.cheprasov.kmmapp.di

import org.koin.dsl.module
import yegor.cheprasov.kmmapp.GameSource

val gameSourceModule = module {
    factory { GameSource(get()) }
}