package by.home.dartlen.weathertesttask.di

import by.home.dartlen.weathertesttask.Constants.BASE_URL
import by.home.dartlen.weathertesttask.model.Repository
import by.home.dartlen.weathertesttask.model.data.AppDatabase
import by.home.dartlen.weathertesttask.model.network.WeatherService
import by.home.dartlen.weathertesttask.model.system.AppSchedulers
import by.home.dartlen.weathertesttask.mvp.historylist.Tab2Contract
import by.home.dartlen.weathertesttask.mvp.historylist.Tab2Presenter
import by.home.dartlen.weathertesttask.mvp.weather.Tab1Contract
import by.home.dartlen.weathertesttask.mvp.weather.Tab1Presenter
import by.home.dartlen.weathertesttask.mvp.weatherhistory.WeatherHistoryContract
import by.home.dartlen.weathertesttask.mvp.weatherhistory.WeatherHistoryPresenter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single(createOnStart = true) { Repository(get(), get()) }

    factory<Tab1Contract.Presenter> { Tab1Presenter(get(), get()) }

    factory<Tab2Contract.Presenter> { Tab2Presenter(get(), get()) }

    factory<WeatherHistoryContract.Presenter> { WeatherHistoryPresenter(get(), get()) }

    single(createOnStart = true) { createOkHttpClient() }

    single(createOnStart = true) { AppSchedulers() }

    single(createOnStart = true) { createWebService<WeatherService>(get(), BASE_URL) }

    single { AppDatabase.getInstance(androidContext()) }
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