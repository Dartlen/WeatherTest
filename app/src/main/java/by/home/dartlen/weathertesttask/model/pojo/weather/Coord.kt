package by.home.dartlen.weathertesttask.model.pojo.weather

import com.google.gson.annotations.SerializedName

data class Coord(

        @field:SerializedName("lon")
        val lon: Double? = null,

        @field:SerializedName("lat")
        val lat: Double? = null
)