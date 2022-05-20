package yegor.cheprasov.kmmapp.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import yegor.cheprasov.kmmapp.android.presentation.viewModel.DetailsViewModel
import yegor.cheprasov.kmmapp.android.presentation.viewModel.MainViewModel
import yegor.cheprasov.kmmapp.android.presentation.viewModel.SearchViewModel

val appModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SearchViewModel() }
    viewModel { DetailsViewModel() }
}