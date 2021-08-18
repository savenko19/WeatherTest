package com.example.weathertest.ui.main.view

import android.app.Activity
import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertest.R
import com.example.weathertest.domain.model.City
import com.example.weathertest.ui.main.presenter.MainPresenter
import com.example.weathertest.ui.main.presenter.MainPresenterFactory
import com.example.weathertest.ui.main.view.adapter.CitiesAdapter
import com.example.weathertest.ui.main.view.adapter.OnCityClickListener
import com.example.weathertest.ui.main.view.adapter.OnEmptySearchListener
import com.example.weathertest.utils.FILE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MainFragment : Fragment(), MainView, OnCityClickListener, OnEmptySearchListener {

    private lateinit var presenter: MainPresenter
    private lateinit var searchEt: EditText

    //Cities
    private lateinit var emptyCity: ConstraintLayout
    private lateinit var citiesRecycler: RecyclerView
    private lateinit var citiesAdapter: CitiesAdapter

    //History
    private lateinit var searchHistoryLayout: ConstraintLayout
    private lateinit var searchRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initView()
    }

    override fun onResume() {
        super.onResume()
        presenter.viewIsReady()
    }

    private fun initView() {
        searchEt = requireView().findViewById(R.id.searchView)

        emptyCity = requireActivity().findViewById(R.id.emptySearchLayout)
        citiesRecycler = requireView().findViewById(R.id.cityRecycler)
        citiesAdapter = CitiesAdapter(this, this)

        searchHistoryLayout = requireView().findViewById(R.id.searchHistoryLayout)
        searchRecycler = requireView().findViewById(R.id.historyRecycler)

        searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0?.toString().equals("")) citiesAdapter.filter.filter(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

    }

    private fun initPresenter() {
        presenter = MainPresenterFactory.createPresenter(requireContext().applicationContext)
        presenter.onAttachView(this)
    }

    override fun showCities() {
        val jsonFileString =
            getJsonDataFromAsset(requireContext().applicationContext)

        val gson = Gson()
        val listPersonType = object : TypeToken<ArrayList<City>>() {}.type

        val cities: ArrayList<City> = gson.fromJson(jsonFileString, listPersonType)
        citiesAdapter.cities = cities
        citiesRecycler.adapter = citiesAdapter

    }

    override fun showSearchHistory(searchCities: ArrayList<City>?) {
        if (searchCities != null && searchCities.isNotEmpty()) {
            searchHistoryLayout.visibility = View.VISIBLE
            val searchAdapter = CitiesAdapter(this, this)
            searchAdapter.cities = searchCities
            searchRecycler.adapter = searchAdapter
        }
    }

    private fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(FILE_NAME).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun onCityClick(city: City) {
        hideKeyboard(requireActivity())
        searchEt.text.clear()
        val action = MainFragmentDirections.actionMainFragmentToCityFragment(
            city.coordinates.first(), city.coordinates.last(), city.name
        )
        requireView().findNavController().navigate(action)
    }

    override fun onEmptySearch(
        lat: Long,
        lon: Long,
        isEmpty: Boolean
    ) {

        try {
            val geocoder = Geocoder(requireContext())
            val addresses = geocoder.getFromLocationName(searchEt.text.toString(), 1)
            for (address in addresses) {
                if (address.hasLatitude() && address.hasLongitude()) {
                    emptyCity.findViewById<TextView>(R.id.cityNameTv).text = searchEt.text
                    searchHistoryLayout.visibility = if (isEmpty) View.GONE else View.VISIBLE
                    citiesRecycler.visibility = if (isEmpty) View.GONE else View.VISIBLE
                    emptyCity.visibility = if (isEmpty) View.VISIBLE else View.GONE
                    emptyCity.setOnClickListener {
                        hideKeyboard(requireActivity())
                        presenter.addCitySearch(
                            City(
                                searchEt.text.toString(),
                                ArrayList(
                                    listOf(
                                        address.latitude.toLong(),
                                        address.longitude.toLong()
                                    )
                                )
                            )
                        )

                        val action = MainFragmentDirections.actionMainFragmentToCityFragment(
                            address.latitude.toLong(),
                            address.longitude.toLong(),
                            searchEt.text.toString()
                        )
                        requireView().findNavController().navigate(action)
                        searchEt.text.clear()
                    }
                }
            }
        } catch (ex: Exception) {
            citiesRecycler.visibility = View.VISIBLE
            Log.e("Test", "Ex geo: ${ex.localizedMessage}")
        }

    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        var view = activity.currentFocus

        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}