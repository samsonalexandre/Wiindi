package com.example.wiindi.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wiindi.MainViewModel
import com.example.wiindi.R
import com.example.wiindi.adapters.VpAdapter
import com.example.wiindi.databinding.FragmentMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.material.tabs.TabLayoutMediator

const val API_KEY = "d57636e8b33d45d79ab155621232609"
class MainFragment : Fragment() {

    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private val model : MainViewModel by activityViewModels()

    private val fList = listOf(
        HoursFragment.newInstance(),
        DaysFragment.newInstance()
    )
    private val tList = listOf(
        "Stunden",
        "Tage"
    )
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
        requestWeatherData("Neuss")
    }

    private fun init() = with(binding) {
        val adapter = VpAdapter(activity as FragmentActivity, fList)
        vp.adapter = adapter
        TabLayoutMediator(tabLayout, vp) {
            tab, pos -> tab.text = tList[pos]
        }.attach()
    }


    //Prüfe ob Location erlaubt ist
    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()) {
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }
    //Prüfe ob Location erlaubt ist
    private fun checkPermission() {
        if (isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun requestWeatherData(city: String) {
        val url = "http://api.weatherapi.com/v1/forecast.json?key= + " +
                API_KEY +
                "&q= + " +
                city +
                "&days= + " +
                "4 + " +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                result ->Log.d("MyLog", "Error: $result")
            },
            {
                error -> Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(request)
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()

    }
}