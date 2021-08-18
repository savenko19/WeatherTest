package com.example.weathertest.ui.main.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertest.R
import com.example.weathertest.domain.model.City

class CitiesAdapter(
    private val onCityClickListener: OnCityClickListener,
    private val onEmptySearchListener: OnEmptySearchListener
) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>(), Filterable {

    private var citiesFilterList = ArrayList<City>()

    var cities: ArrayList<City> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            citiesFilterList = value
            notifyDataSetChanged()
        }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                citiesFilterList = if (charSearch.isEmpty()) {
                    cities
                } else {

                    val coordinates = charSearch.split(".").toTypedArray()
                    val resultList = ArrayList<City>()
                    for (city in cities) {
                        if (city.name.contains(charSearch, true)) resultList.add(city)
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = citiesFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if ((results?.values as ArrayList<*>).isEmpty()) {
                    onEmptySearchListener.onEmptySearch(55, 45, true)
                } else {
                    onEmptySearchListener.onEmptySearch(55, 45, false)
                }
                citiesFilterList = results?.values as ArrayList<City>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item_layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(citiesFilterList[position])
    }

    override fun getItemCount() = citiesFilterList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val nameOfCity: TextView = itemView.findViewById(R.id.cityNameTv)

        fun bind(city: City) {
            nameOfCity.text = city.name
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onCityClickListener.onCityClick(cities[adapterPosition])
        }
    }
}