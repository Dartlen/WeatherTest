package by.home.dartlen.weathertesttask.mvp.weatherhistory

import by.home.dartlen.weathertesttask.common.with
import by.home.dartlen.weathertesttask.model.Repository
import by.home.dartlen.weathertesttask.model.system.AppSchedulers
import by.home.dartlen.weathertesttask.mvp.base.RxPresenter

class WeatherHistoryPresenter(val repository: Repository, val appSchedulers: AppSchedulers) :
        RxPresenter<WeatherHistoryContract.View>(), WeatherHistoryContract.Presenter {
    override var view: WeatherHistoryContract.View? = null

    override fun start(id: Int) {
        launch {
            repository.getWeatherById(id).with(appSchedulers).subscribe({ t ->
                view!!.showWeather(t)
            }, {

            })
        }
    }
}