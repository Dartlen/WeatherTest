package by.home.dartlen.weathertesttask.model.pojo.weather

import com.google.gson.annotations.SerializedName

data class Wind(

        @field:SerializedName("deg")
        val deg: Any? = null,

        @field:SerializedName("speed")
        val speed: Any? = null
)