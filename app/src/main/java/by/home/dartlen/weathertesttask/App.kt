package by.home.dartlen.weathertesttask

import android.app.Application
import by.home.dartlen.weathertesttask.di.appModule
import org.koin.android.ext.android.startKoin

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}