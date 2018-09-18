package by.home.dartlen.weathertesttask.mvp.weather

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.home.dartlen.weathertesttask.R
import org.koin.android.ext.android.inject
import android.location.LocationListener
import android.util.Log
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import android.content.Intent
import androidx.appcompat.app.AlertDialog


@RuntimePermissions
class Tab1Fragment: Fragment(), Tab1Contract.View, LocationListener{

    override val presenter: Tab1Contract.Presenter by inject()
    lateinit var locationManager: LocationManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }


    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
        locationManager = activity!!.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps()
        }
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000 * 10, 10f, this)
        } catch (e: SecurityException) {
            Log.e("", "Fail to request location update", e)
        } catch (e: IllegalArgumentException) {
            Log.e("", "GPS provider does not exist", e)
        }
    }

    private fun buildAlertMessageNoGps() {
        val builder = AlertDialog.Builder(activity!!)
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS))}
                .setNegativeButton("No") { dialog, id -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }

    override fun onLocationChanged(location: Location) {

        Log.d(location.longitude.toString(), location.altitude.toString())
    }

    override fun onProviderDisabled(provider: String) {
    }

    override fun onProviderEnabled(provider: String) {
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

}