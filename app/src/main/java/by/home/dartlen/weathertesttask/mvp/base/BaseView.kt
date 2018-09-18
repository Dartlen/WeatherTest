package by.home.dartlen.weathertesttask.mvp.base

interface BaseView<out T : BasePresenter<*>> {

    val presenter: T
}