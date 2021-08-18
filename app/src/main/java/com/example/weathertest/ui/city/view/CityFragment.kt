package com.example.weathertest.ui.city.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertest.R
import com.example.weathertest.domain.model.TimeForecast
import com.example.weathertest.domain.model.WeatherOfCity
import com.example.weathertest.ui.city.presenter.CityPresenter
import com.example.weathertest.ui.city.presenter.CityPresenterFactory
import com.example.weathertest.ui.city.view.adapter.date.DateAdapter
import com.example.weathertest.ui.city.view.adapter.hour.HoursAdapter
import com.example.weathertest.utils.formatTemp


class CityFragment : Fragment(), CityView {

    private val args: CityFragmentArgs by navArgs()
    private lateinit var presenter: CityPresenter

    private lateinit var cityNameTv: TextView
    private lateinit var weatherProgress: ProgressBar

    private lateinit var datesRecycler: RecyclerView
    private lateinit var dateAdapter: DateAdapter

    private lateinit var hoursRecycler: RecyclerView
    private lateinit var hoursAdapter: HoursAdapter

    //currentData
    private lateinit var currentTemp: TextView
    private lateinit var currentCondition: TextView
    private lateinit var conditionIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initView()
    }

    override fun onResume() {
        super.onResume()
        presenter.viewIsReady()
        presenter.getWeatherOfCity(args.lat, args.lon)
    }

    private fun initView() {
        cityNameTv = requireView().findViewById(R.id.cityTitleTv)
        cityNameTv.text = args.name

        weatherProgress = requireView().findViewById(R.id.infoWeatherProgress)

        datesRecycler = requireView().findViewById(R.id.dateRecycler)
        dateAdapter = DateAdapter()

        hoursRecycler = requireView().findViewById(R.id.hourRecycler)
        hoursAdapter = HoursAdapter()

        currentTemp = requireView().findViewById(R.id.currentTempTv)
        currentCondition = requireView().findViewById(R.id.currentConditionTv)
        conditionIcon = requireView().findViewById(R.id.conditionIconIv)

    }

    private fun initPresenter() {
        presenter = CityPresenterFactory.createPresenter(requireContext().applicationContext)
        presenter.onAttachView(this)
    }

    override fun showWeather(weather: WeatherOfCity) {
        weatherProgress.visibility = View.GONE
        currentTemp.text = formatTemp(weather.currentTemp)
        currentCondition.text = weather.currentCondition

        val hours = ArrayList<TimeForecast>()
        for ((key, value) in weather.hourlyForecast) {
            hours.add(TimeForecast((key.toInt() + 1).toString(), value))
        }

        hours.apply { sortBy { it.hourValue.toInt() } }
        hoursAdapter.hours = hours
        hoursRecycler.adapter = hoursAdapter

        val dates = ArrayList<TimeForecast>()
        for ((key, value) in weather.datesForecasts) {
            dates.add(TimeForecast(key, value))
        }

        dateAdapter.dates = dates
        datesRecycler.adapter = dateAdapter
    }


}