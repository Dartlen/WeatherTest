package by.home.dartlen.weathertesttask.mvp.history

import by.home.dartlen.weathertesttask.model.Repository
import by.home.dartlen.weathertesttask.mvp.base.RxPresenter

class Tab2Presenter(val repository: Repository): RxPresenter<Tab2Contract.View>(), Tab2Contract.Presenter  {
    override var view: Tab2Contract.View? = null
}