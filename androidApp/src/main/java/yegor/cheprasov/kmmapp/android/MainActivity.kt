package yegor.cheprasov.kmmapp.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import yegor.cheprasov.kmmapp.android.presentation.navigation.NavController
import yegor.cheprasov.kmmapp.android.presentation.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val rememberNavController = rememberNavController()
            NavController(navController = rememberNavController)
        }
    }
}
