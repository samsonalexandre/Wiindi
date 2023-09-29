package com.example.wiindi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiindi.R
import com.example.wiindi.adapters.WeatherAdapter
import com.example.wiindi.adapters.WeatherModel
import com.example.wiindi.databinding.FragmentHoursBinding


class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    private fun initRcView() = with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter
        val list = listOf(
            WeatherModel(
                "","12:00",
                "Sonnig","25ºC",
                "", "", "",""),
            WeatherModel(
                "","13:00",
                "Sonnig","27ºC",
                "", "", "",""),
            WeatherModel(
                "","14:00",
                "Sonnig","35ºC",
                "", "", "","")
        )
        adapter.submitList(list)
    }

    companion object {

        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}