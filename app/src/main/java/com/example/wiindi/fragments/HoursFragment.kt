package com.example.wiindi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiindi.MainViewModel
import com.example.wiindi.R
import com.example.wiindi.adapters.WeatherAdapter
import com.example.wiindi.adapters.WeatherModel
import com.example.wiindi.databinding.FragmentHoursBinding
import org.json.JSONArray
import org.json.JSONObject

//Ein Fragment, das eine Liste von Wetterdaten für Stunden anzeigt.
class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    //wird aufgerufen, wenn das Fragment erstellt wurde. Hier wird initRcView()
    // aufgerufen und die LiveData des ViewModels beobachtet, um die Stundenwetterdaten in den RecyclerView zu laden.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            Log.d("MyLog", "Hours: ${it.hours}")
            adapter.submitList(getHoursList(it))
        }
    }

    //Initialisiert den RecyclerView und den Adapter.
    private fun initRcView() = with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        rcView.adapter = adapter

    }

    //Wandelt die Stundenwetterdaten in eine Liste von WeatherModel-Objekten um.
    private fun getHoursList(wItem: WeatherModel): List<WeatherModel> {
        val hoursArray = JSONArray(wItem.hours)
        val list = ArrayList<WeatherModel>()
        for (i in 0 until hoursArray.length()) {
            val item = WeatherModel(
                wItem.city,
                (hoursArray[i] as JSONObject).getString("time"),
                (hoursArray[i] as JSONObject).getJSONObject("condition")
                    .getString("text"),
                (hoursArray[i] as JSONObject).getString("temp_c"),
                "",
                "",
                (hoursArray[i] as JSONObject).getJSONObject("condition")
                    .getString("icon"),
                ""
            )
            list.add(item)
        }
        return list
    }

    //Erstelle und gebe eine neue Instanz der HoursFragment-Klasse zurück.
    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}