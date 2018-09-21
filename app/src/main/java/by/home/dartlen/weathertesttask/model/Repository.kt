package by.home.dartlen.weathertesttask.model

import by.home.dartlen.weathertesttask.model.data.AppDatabase
import by.home.dartlen.weathertesttask.model.data.Weather
import by.home.dartlen.weathertesttask.model.network.WeatherService
import by.home.dartlen.weathertesttask.model.pojo.weather.WeatherResponse
import io.reactivex.Single

class Repository(val weatherService: WeatherService,
                 val appDatabase: AppDatabase) {

    fun getWeather(lat: Double, lon: Double): Single<WeatherResponse> {
        return weatherService.getWeather(lat, lon, "metric", "b2945937716459a7faa721b2c25a60ee")
    }

    fun setWeather(weather: Weather) {
        appDatabase.weatherDao().insert(weather)
    }

    fun getWeathers(): Single<List<Weather>> {
        return appDatabase.weatherDao().getWeathers()
    }

    fun getWeatherById(id: Int): Single<Weather> {
        return appDatabase.weatherDao().getWeatherById(id)
    }
}