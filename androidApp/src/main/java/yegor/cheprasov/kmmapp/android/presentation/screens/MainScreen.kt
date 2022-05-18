package yegor.cheprasov.kmmapp.android.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import yegor.cheprasov.kmmapp.android.presentation.viewModel.MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = getViewModel()
) {
    MainScreen()
}

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Cyan
    )
    {

    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}

