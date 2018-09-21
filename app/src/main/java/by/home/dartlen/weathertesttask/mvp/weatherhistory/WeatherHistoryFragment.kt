package by.home.dartlen.weathertesttask.mvp.weatherhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.home.dartlen.weathertesttask.R
import by.home.dartlen.weathertesttask.model.data.Weather
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tab1.*
import org.koin.android.ext.android.inject

class WeatherHistoryFragment : Fragment(), WeatherHistoryContract.View {
    companion object {
        fun newInstance(id: Any) = WeatherHistoryFragment()
                .apply {
                    arguments = Bundle()
                            .apply { putInt("id", id as Int) }
                }
    }

    override val presenter: WeatherHistoryContract.Presenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.start(arguments!!["id"] as Int)
    }

    override fun showWeather(t: Weather) {
        activity!!.weatherhistory.visibility = View.VISIBLE
        temperature.text = t.temp.toString()
        pressure.text = t.pressure.toString()
        humidity.text = t.humidity.toString()
        wind.text = t.wild.toString()
    }
}