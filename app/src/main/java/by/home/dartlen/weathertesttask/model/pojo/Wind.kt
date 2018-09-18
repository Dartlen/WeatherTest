package by.home.dartlen.weathertesttask.model.pojo

import com.google.gson.annotations.SerializedName

data class Wind(

	@field:SerializedName("deg")
	val deg: Int? = null,

	@field:SerializedName("speed")
	val speed: Int? = null
)