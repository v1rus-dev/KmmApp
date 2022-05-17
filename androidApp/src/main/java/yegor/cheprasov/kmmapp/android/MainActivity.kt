package yegor.cheprasov.kmmapp.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import yegor.cheprasov.kmmapp.Greeting
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import yegor.cheprasov.kmmapp.data.Ktor
import yegor.cheprasov.kmmapp.data.api.GamesApi

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val list = GamesApi(Ktor()).getGames()
            Log.d("myTag", "$list")
        }

        setContent {
            Scaffold {

            }
        }
    }
}
