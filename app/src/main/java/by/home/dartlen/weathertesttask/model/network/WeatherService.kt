package by.home.dartlen.weathertesttask.model.network

import by.home.dartlen.weathertesttask.model.pojo.weather.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/weather")//?lat={lat}&lon={lon}&units=metric&appid={key}
    fun getWeather(@Query("lat") lat: Double, @Query("lon") lon: Double,
                   @Query("units") format: String, @Query("appid") key: String):
            Single<WeatherResponse>

    //53.907303 27.576277 apikey=b2945937716459a7faa721b2c25a60ee
}