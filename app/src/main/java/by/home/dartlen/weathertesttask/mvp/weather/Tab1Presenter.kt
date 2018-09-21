package by.home.dartlen.weathertesttask.mvp.weather

import android.location.Location
import android.util.Log
import by.home.dartlen.weathertesttask.common.with
import by.home.dartlen.weathertesttask.model.Repository
import by.home.dartlen.weathertesttask.model.data.Weather
import by.home.dartlen.weathertesttask.model.pojo.weather.WeatherResponse
import by.home.dartlen.weathertesttask.model.system.AppSchedulers
import by.home.dartlen.weathertesttask.mvp.base.RxPresenter
import java.util.*

class Tab1Presenter(val repository: Repository, val appSchedulers: AppSchedulers) : RxPresenter<Tab1Contract.View>(), Tab1Contract.Presenter {
    override var view: Tab1Contract.View? = null
    var weather: WeatherResponse? = null
    override fun getWeather(location: Location) {

        launch {
            repository.getWeather(location.latitude, location.longitude).with(appSchedulers)
                    .subscribe({ t ->
                        weather = t
                        view!!.showWeather(t.main!!, t.wind!!, t.sys!!)
                    }, { t ->
                        Log.d("", "")
                    })
        }
    }

    override fun saveResult(city: String?) {
        repository.setWeather(Weather(null, weather!!.main!!.temp!!, weather!!.main!!.humidity!!,
                weather!!.main!!.pressure!!, weather!!.wind!!.speed as Double,
                Calendar.getInstance().time, city!!, weather!!.coord!!.lat!!, weather!!.coord!!.lon!!))
    }
}