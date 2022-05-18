package yegor.cheprasov.kmmapp.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import yegor.cheprasov.kmmapp.android.presentation.viewModel.MainViewModel
import yegor.cheprasov.kmmapp.android.useCase.GameSource

val appModule = module {
    viewModel { MainViewModel(get()) }
}

val gameSourceModule = module {
    factory { GameSource(get()) }
}