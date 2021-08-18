package com.example.weathertest.ui.city.view.adapter.date

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertest.R
import com.example.weathertest.domain.model.TimeForecast
import com.example.weathertest.utils.formatTemp

class DateAdapter : RecyclerView.Adapter<DateAdapter.ViewHolder>() {

    var dates = ArrayList<TimeForecast>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.date_item_layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dates[position])
    }

    override fun getItemCount() = dates.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTitle: TextView = itemView.findViewById(R.id.dateValueTv)
        private val tameValue: TextView = itemView.findViewById(R.id.tempValueTv)

        fun bind(date: TimeForecast) {
            dateTitle.text = date.hourValue
            tameValue.text = formatTemp(date.tempValue)
        }
    }
}