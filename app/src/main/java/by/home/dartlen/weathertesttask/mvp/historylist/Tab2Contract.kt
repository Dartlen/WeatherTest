package by.home.dartlen.weathertesttask.mvp.historylist

import by.home.dartlen.weathertesttask.model.data.Weather
import by.home.dartlen.weathertesttask.mvp.base.BasePresenter
import by.home.dartlen.weathertesttask.mvp.base.BaseView

interface Tab2Contract {
    interface View : BaseView<Presenter> {
        fun showWeathers(list: List<Weather>)
        fun showWeatherHistory(id: Int)
    }

    interface Presenter : BasePresenter<View> {
        fun start()
        fun onClickedItem(item: Weather)
    }
}