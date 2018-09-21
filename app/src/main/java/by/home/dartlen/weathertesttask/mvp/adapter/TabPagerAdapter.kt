package by.home.dartlen.weathertesttask.mvp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import by.home.dartlen.weathertesttask.mvp.historylist.Tab2Fragment
import by.home.dartlen.weathertesttask.mvp.weather.Tab1Fragment

class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        return when (position) {
            0 -> Tab1Fragment()
            1 -> Tab2Fragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItemPosition(`object`: Any): Int {

        return super.getItemPosition(`object`)
    }
}