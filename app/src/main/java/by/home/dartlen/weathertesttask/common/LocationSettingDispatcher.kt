package by.home.dartlen.weathertesttask.common

import android.app.Activity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import io.reactivex.Maybe
import io.reactivex.MaybeEmitter
import io.reactivex.MaybeOnSubscribe
import io.reactivex.subjects.PublishSubject

class LocationSettingDispatcher(activity: Activity) {

    private val maybeSubscribe = UpdateLocationMaybeSubscribe(activity)
    private val subject = PublishSubject.create<Boolean>()

    companion object {
        val REQUEST_CHECK_SETTINGS = 0x1
    }

    fun onActivityResult(requestCode: Int, resultCode: Int) {
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            subject.onNext(resultCode == Activity.RESULT_OK)
        }
    }

    fun asMaybe(): Maybe<Boolean> {
        return Maybe.create(maybeSubscribe)
                .switchIfEmpty(subject.firstElement())
    }

    class UpdateLocationMaybeSubscribe(private val activity: Activity) : MaybeOnSubscribe<Boolean> {

        private val locationRequest = LocationRequest().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        private val client = LocationServices.getSettingsClient(activity)
        private val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        override fun subscribe(emitter: MaybeEmitter<Boolean>) {

            val task = client.checkLocationSettings(builder.build())
            task.addOnSuccessListener(activity, { emitter.onSuccess(true) })
            task.addOnFailureListener(activity, { exception ->
                exception as ApiException
                if (exception.statusCode == CommonStatusCodes.RESOLUTION_REQUIRED) {
                    try {
                        exception as ResolvableApiException
                        exception.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS)
                        // ниже строчка необходима чтобы свалиться в switchIfEmpty и ждать ответа пользователя
                        emitter.onComplete()
                    } catch (exception1: Exception) {
                        emitter.onError(exception1)
                    }
                } else emitter.onSuccess(false)
            })
        }
    }


}