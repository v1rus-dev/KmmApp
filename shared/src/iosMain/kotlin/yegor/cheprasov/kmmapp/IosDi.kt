package yegor.cheprasov.kmmapp

import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import yegor.cheprasov.kmmapp.di.gamesApiModule
import yegor.cheprasov.kmmapp.di.gamesRepositoryModule
import yegor.cheprasov.kmmapp.di.ktorModule

fun initKoinIos() = startKoin {
    modules(ktorModule, gamesApiModule, gamesRepositoryModule)
}

class IosComponent : KoinComponent {

}