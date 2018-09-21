package by.home.dartlen.weathertesttask

import android.app.Application
import androidx.multidex.MultiDex
import by.home.dartlen.weathertesttask.di.appModule
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
        JodaTimeAndroid.init(this)
        MultiDex.install(this)
    }
}