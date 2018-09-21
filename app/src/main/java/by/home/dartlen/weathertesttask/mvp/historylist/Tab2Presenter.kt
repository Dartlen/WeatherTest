package by.home.dartlen.weathertesttask.mvp.historylist

import by.home.dartlen.weathertesttask.common.with
import by.home.dartlen.weathertesttask.model.Repository
import by.home.dartlen.weathertesttask.model.data.Weather
import by.home.dartlen.weathertesttask.model.system.AppSchedulers
import by.home.dartlen.weathertesttask.mvp.base.RxPresenter

class Tab2Presenter(val repository: Repository, val schedulers: AppSchedulers) : RxPresenter<Tab2Contract.View>(), Tab2Contract.Presenter {
    override var view: Tab2Contract.View? = null

    override fun start() {
        launch {
            repository.getWeathers().with(schedulers).subscribe({ t ->
                view!!.showWeathers(t)

            }, {

            })
        }
    }

    override fun onClickedItem(item: Weather) {
        view!!.showWeatherHistory(item.id!!)
    }
}