package com.example.wiindi.adapters

//Dies ist eine einfache Datenklasse, die die Wetterdaten für eine bestimmte Stadt darstellt.
data class WeatherModel(
    val city: String,
    val time: String,
    val condition: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val imageUrl: String,
    val hours: String
)