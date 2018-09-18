package by.home.dartlen.weathertesttask.mvp.weather

import by.home.dartlen.weathertesttask.mvp.base.BasePresenter
import by.home.dartlen.weathertesttask.mvp.base.BaseView

interface Tab1Contract{
    interface View:BaseView<Presenter>{

    }
    interface Presenter: BasePresenter<View> {

    }
}