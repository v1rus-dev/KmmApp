package yegor.cheprasov.kmmapp.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import yegor.cheprasov.kmmapp.android.di.appModule
import yegor.cheprasov.kmmapp.android.di.gameSourceModule
import yegor.cheprasov.kmmapp.di.initKoin

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, gameSourceModule)
        }
        instance = this
    }

}