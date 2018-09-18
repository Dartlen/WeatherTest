package by.home.dartlen.weathertesttask.di

import by.home.dartlen.weathertesttask.Constants.BASE_URL
import by.home.dartlen.weathertesttask.model.Repository
import by.home.dartlen.weathertesttask.model.network.WeatherService
import by.home.dartlen.weathertesttask.mvp.weather.Tab2Contract
import by.home.dartlen.weathertesttask.mvp.weather.Tab2Presenter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single(createOnStart = true)  { Repository(get()) }

    factory<Tab2Contract.Presenter> { Tab2Presenter(get()) }

    single(createOnStart = true)  { createOkHttpClient() }

    single(createOnStart = true)  { createWebService<WeatherService>(get(), BASE_URL) }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}