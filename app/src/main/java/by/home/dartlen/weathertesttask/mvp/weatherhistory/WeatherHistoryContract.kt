package by.home.dartlen.weathertesttask.mvp.weatherhistory

import by.home.dartlen.weathertesttask.model.data.Weather
import by.home.dartlen.weathertesttask.mvp.base.BasePresenter
import by.home.dartlen.weathertesttask.mvp.base.BaseView

interface WeatherHistoryContract {
    interface View : BaseView<Presenter> {
        fun showWeather(t: Weather)
    }

    interface Presenter : BasePresenter<View> {
        fun start(id: Int)
    }
}