package by.home.dartlen.weathertesttask.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "weather")
data class Weather(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int?,
        val temp: Double = 0.0,
        val humidity: Double = 0.0,
        val pressure: Double = 0.0,
        val wild: Double = 0.0,
        var createdAt: Date? = Date(),
        val city: String = "",
        val lng: Double = 0.0,
        val lon: Double = 0.0
)