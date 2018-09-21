package by.home.dartlen.weathertesttask.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather ORDER BY id")
    fun getWeathers(): Single<List<Weather>>

    @Query("SELECT * FROM weather WHERE id=:id")
    fun getWeatherById(id: Int): Single<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: Weather)
}