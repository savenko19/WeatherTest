package com.example.weathertest.ui.city.view.adapter.hour

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertest.R
import com.example.weathertest.domain.model.TimeForecast
import com.example.weathertest.utils.formatHour
import com.example.weathertest.utils.formatTemp

class HoursAdapter : RecyclerView.Adapter<HoursAdapter.ViewHolder>() {

    var hours = ArrayList<TimeForecast>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.hour_item_layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hours[position])
    }

    override fun getItemCount() = hours.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTitle: TextView = itemView.findViewById(R.id.hourValueTv)
        private val tameValue: TextView = itemView.findViewById(R.id.tempValueTv)

        fun bind(timeForecast: TimeForecast) {
            dateTitle.text = formatHour(timeForecast.hourValue.toInt())
            tameValue.text = formatTemp(timeForecast.tempValue)
        }
    }
}