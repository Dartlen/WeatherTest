package by.home.dartlen.weathertesttask.mvp.weather

import android.location.Location
import by.home.dartlen.weathertesttask.model.pojo.weather.Main
import by.home.dartlen.weathertesttask.model.pojo.weather.Sys
import by.home.dartlen.weathertesttask.model.pojo.weather.Wind
import by.home.dartlen.weathertesttask.mvp.base.BasePresenter
import by.home.dartlen.weathertesttask.mvp.base.BaseView

interface Tab1Contract {
    interface View : BaseView<Presenter> {
        fun showWeather(main: Main, wind: Wind, sys: Sys)
    }

    interface Presenter : BasePresenter<View> {
        fun getWeather(location: Location)
        fun saveResult(city: String?)
    }
}