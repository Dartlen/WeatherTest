package by.home.dartlen.weathertesttask.mvp.historylist.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.home.dartlen.weathertesttask.R
import by.home.dartlen.weathertesttask.common.inflate
import by.home.dartlen.weathertesttask.model.data.Weather
import kotlinx.android.synthetic.main.item_history.view.*
import net.danlew.android.joda.DateUtils
import org.joda.time.DateTime

class WeatherAdapter(private val listener: (Weather) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<Weather>? = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_history))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(list!![position], listener)
    }

    override fun getItemCount(): Int {
        return if (list!!.isEmpty()) 0 else list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Weather, listener: (Weather) -> Unit) {
            itemView.geo.text = item.lng.toString() + "," + item.lon.toString()
            itemView.city.text = item.city
            itemView.date.text = DateUtils.formatDateTime(itemView.context, DateTime(item.createdAt), DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME)
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}