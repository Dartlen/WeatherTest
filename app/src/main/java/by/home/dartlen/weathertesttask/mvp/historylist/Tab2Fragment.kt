package by.home.dartlen.weathertesttask.mvp.historylist

import android.Manifest
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.home.dartlen.weathertesttask.R
import by.home.dartlen.weathertesttask.model.data.Weather
import by.home.dartlen.weathertesttask.mvp.historylist.adapter.WeatherAdapter
import by.home.dartlen.weathertesttask.mvp.weatherhistory.WeatherHistoryFragment
import kotlinx.android.synthetic.main.fragment_tab2.*
import org.koin.android.ext.android.inject
import permissions.dispatcher.NeedsPermission


class Tab2Fragment : Fragment(), Tab2Contract.View {

    override val presenter: Tab2Contract.Presenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linerLayoutManager = LinearLayoutManager(activity)
        val itemDecor = DividerItemDecoration(activity, HORIZONTAL)
        recyclerView.layoutManager = linerLayoutManager
        recyclerView.addItemDecoration(itemDecor)
        recyclerView.adapter = WeatherAdapter { item: Weather -> presenter.onClickedItem(item) }
    }


    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)

    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun showWeathers(list: List<Weather>) {
        (recyclerView.adapter as WeatherAdapter).list = list
        (recyclerView.adapter as WeatherAdapter).notifyDataSetChanged()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            presenter.start()
        }
    }

    override fun showWeatherHistory(id: Int) {
        activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.weatherhistory, WeatherHistoryFragment.newInstance(id), "weatherhistory")
                .commit()
    }

}