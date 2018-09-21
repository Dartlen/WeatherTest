package by.home.dartlen.weathertesttask.mvp.base

interface BasePresenter<V> {

    fun subscribe(view: V)

    fun unSubscribe()

    var view: V?

}