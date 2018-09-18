package by.home.dartlen.weathertesttask.mvp.weather

import by.home.dartlen.weathertesttask.model.Repository
import by.home.dartlen.weathertesttask.mvp.base.RxPresenter

class Tab1Presenter(val repository: Repository): RxPresenter<Tab1Contract.View>(), Tab1Contract.Presenter  {
    override var view: Tab1Contract.View? = null
}