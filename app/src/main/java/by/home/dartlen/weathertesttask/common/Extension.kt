package by.home.dartlen.weathertesttask.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.home.dartlen.weathertesttask.model.system.SchedulerProvider
import io.reactivex.Single

fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> =
        this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}