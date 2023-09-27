package com.example.wiindi

import android.app.DownloadManager.Request
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.wiindi.databinding.ActivityMainBinding
import com.example.wiindi.fragments.MainFragment
import org.json.JSONObject


const val API_KEY = "d57636e8b33d45d79ab155621232609"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeHolder, MainFragment.newInstance())
            .commit()

    }

//    private fun getResult(name: String) {
//        val url = "http://api.weatherapi.com/v1/current.json" +
//                "?key=$API_KEY&q=$name&aqi=no"
//    }
}