package by.home.dartlen.weathertesttask.mvp.history

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.home.dartlen.weathertesttask.R
import org.koin.android.ext.android.inject
import permissions.dispatcher.NeedsPermission

class Tab2Fragment : Fragment(), Tab2Contract.View {

    override val presenter: Tab2Contract.Presenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab2, container, false)
    }


    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)

    }

}