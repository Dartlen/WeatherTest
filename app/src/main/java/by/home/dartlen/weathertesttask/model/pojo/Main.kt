package by.home.dartlen.weathertesttask.model.pojo

import com.google.gson.annotations.SerializedName

data class Main(

	@field:SerializedName("temp")
	val temp: Int? = null,

	@field:SerializedName("temp_min")
	val tempMin: Int? = null,

	@field:SerializedName("humidity")
	val humidity: Int? = null,

	@field:SerializedName("pressure")
	val pressure: Int? = null,

	@field:SerializedName("temp_max")
	val tempMax: Int? = null
)