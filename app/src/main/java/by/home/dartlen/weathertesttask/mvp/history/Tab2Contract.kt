package by.home.dartlen.weathertesttask.mvp.history

import by.home.dartlen.weathertesttask.mvp.base.BasePresenter
import by.home.dartlen.weathertesttask.mvp.base.BaseView

interface Tab2Contract{
    interface View:BaseView<Presenter>{

    }
    interface Presenter: BasePresenter<View> {

    }
}