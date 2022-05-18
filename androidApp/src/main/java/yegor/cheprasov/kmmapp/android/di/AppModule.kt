package yegor.cheprasov.kmmapp.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import yegor.cheprasov.kmmapp.android.presentation.viewModel.MainViewModel

val appModule = module {
    viewModel { MainViewModel(get()) }
}