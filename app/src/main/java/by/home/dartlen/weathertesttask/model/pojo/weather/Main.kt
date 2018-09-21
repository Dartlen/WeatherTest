package by.home.dartlen.weathertesttask.model.pojo.weather

import com.google.gson.annotations.SerializedName

data class Main(

        @field:SerializedName("temp")
        val temp: Double? = null,

        @field:SerializedName("temp_min")
        val tempMin: Double? = null,

        @field:SerializedName("humidity")
        val humidity: Double? = null,

        @field:SerializedName("pressure")
        val pressure: Double? = null,

        @field:SerializedName("temp_max")
        val tempMax: Double? = null
)