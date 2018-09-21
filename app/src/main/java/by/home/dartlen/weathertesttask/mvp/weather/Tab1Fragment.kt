package by.home.dartlen.weathertesttask.mvp.weather

import android.Manifest
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.home.dartlen.weathertesttask.Constants
import by.home.dartlen.weathertesttask.R
import by.home.dartlen.weathertesttask.common.LocationDeviceInteractor
import by.home.dartlen.weathertesttask.model.FetchAddressIntentService
import by.home.dartlen.weathertesttask.model.pojo.weather.Main
import by.home.dartlen.weathertesttask.model.pojo.weather.Sys
import by.home.dartlen.weathertesttask.model.pojo.weather.Wind
import kotlinx.android.synthetic.main.fragment_tab1.*
import org.koin.android.ext.android.inject
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class Tab1Fragment : Fragment(), Tab1Contract.View {

    override val presenter: Tab1Contract.Presenter by inject()
    private lateinit var resultReceiver: AddressResultReceiver

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
        resultReceiver = AddressResultReceiver(Handler())
    }

    override fun onStart() {
        super.onStart()
        startWithPermissionCheck()
    }


    override fun showWeather(main: Main, wind: Wind, sys: Sys) {
        temperature.text = main.temp.toString()
        pressure.text = main.pressure.toString()
        humidity.text = main.humidity.toString()
        this.wind.text = wind.speed.toString()
    }

    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    fun start() {

        var locationDeviceInteractor = LocationDeviceInteractor(activity!!)
        //leek
        locationDeviceInteractor
                .getLocation()
                .subscribe({
                    presenter.getWeather(it)
                    startService(it)
                    Log.d(it.latitude.toString(),
                            it.longitude.toString())
                })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    fun startService(lastLocation: Location) {
        val intent = Intent(activity, FetchAddressIntentService::class.java).apply {
            putExtra(Constants.RECEIVER, resultReceiver)
            putExtra(Constants.LOCATION_DATA_EXTRA, lastLocation)
        }
        activity!!.startService(intent)
    }

    private inner class AddressResultReceiver internal constructor(
            handler: Handler
    ) : ResultReceiver(handler) {

        /**
         * Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
         */
        override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
            val addressOutput = resultData.getString(Constants.RESULT_DATA_KEY)
            val cityOutput = resultData.getString(Constants.RESULT_CITY_KEY)

            if (resultCode == Constants.SUCCESS_RESULT) {
                By.text = addressOutput
                presenter.saveResult(cityOutput)
            }
        }
    }
}