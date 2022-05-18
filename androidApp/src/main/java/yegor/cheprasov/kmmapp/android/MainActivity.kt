package yegor.cheprasov.kmmapp.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import yegor.cheprasov.kmmapp.android.presentation.compose.navigation.NavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val rememberNavController = rememberNavController()
            NavController(navController = rememberNavController)
        }
    }
}
