package com.example.wiindi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wiindi.databinding.ActivityMainBinding
import com.example.wiindi.fragments.MainFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeHolder, MainFragment.newInstance())
            .commit()

    }

}