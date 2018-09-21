package by.home.dartlen.weathertesttask.common

import android.app.Activity
import android.location.Location
import com.google.android.gms.location.*
import io.reactivex.Maybe
import io.reactivex.MaybeEmitter
import io.reactivex.MaybeOnSubscribe
import io.reactivex.disposables.Disposables

class LocationDeviceInteractor(activity: Activity) {

    private val provider = LocationServices.getFusedLocationProviderClient(activity)
    private val settingDispatcher = LocationSettingDispatcher(activity)

    private val lastLocationMaybe = Maybe.create<Location> { emitter ->
        try {
            provider.lastLocation.apply {
                addOnSuccessListener { location ->
                    if (location != null) emitter.onSuccess(location)
                    emitter.onComplete()
                }
                addOnFailureListener { emitter.onError(it) }
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }

    private class UpdateLocationMaybeSubscribe(
            private val provider: FusedLocationProviderClient) : MaybeOnSubscribe<Location> {

        private val locationGPSRequest = LocationRequest().apply {
            numUpdates = 1
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        override fun subscribe(emitter: MaybeEmitter<Location>) {
            val callback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    val location = locationResult?.locations?.find { location -> location != null }
                    if (location != null) {
                        removeLocationUpdates(this)
                        emitter.onSuccess(location)
                    }
                    emitter.onComplete()
                }
            }
            provider.requestLocationUpdates(locationGPSRequest, callback, null)
            emitter.setDisposable(Disposables.fromRunnable { removeLocationUpdates(callback) })
        }

        private fun removeLocationUpdates(callback: LocationCallback) {
            provider.removeLocationUpdates(callback)
        }
    }


    fun onActivityResult(requestCode: Int, resultCode: Int) {
        settingDispatcher.onActivityResult(requestCode, resultCode)
    }

    fun getLocation(): Maybe<Location> {
        return lastLocationMaybe
                .switchIfEmpty(settingDispatcher
                        .asMaybe()
                        .flatMap { allow ->
                            if (allow)
                                lastLocationMaybe.switchIfEmpty(
                                        Maybe.create(UpdateLocationMaybeSubscribe(provider)))
                            else
                                Maybe.create(UpdateLocationMaybeSubscribe(provider))
                        })
    }

}