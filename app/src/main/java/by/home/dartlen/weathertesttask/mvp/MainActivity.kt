package by.home.dartlen.weathertesttask.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.home.dartlen.weathertesttask.R
import by.home.dartlen.weathertesttask.mvp.adapter.TabPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        pager.adapter = TabPagerAdapter(supportFragmentManager, 2)

        tabsMain.setupWithViewPager(pager)
        tabsMain.getTabAt(0)!!.text = resources.getString(R.string.tab1)
        tabsMain.getTabAt(1)!!.text = resources.getString(R.string.tab2)
    }
}
