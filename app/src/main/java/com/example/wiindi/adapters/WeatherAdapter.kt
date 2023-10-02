package com.example.wiindi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wiindi.R
import com.example.wiindi.databinding.ListItemBinding
import com.squareup.picasso.Picasso

//Wird verwendet, um eine Liste von Wetterdaten in einem RecyclerView anzuzeigen.
class WeatherAdapter(private val listener: Listener?): ListAdapter<WeatherModel, WeatherAdapter.Holder>(Comparator()) {

    //Die innere Klasse Holder ist eine ViewHolder-Klasse für einzelne Elemente im RecyclerView.
    class Holder(view: View, private val listener: Listener?): RecyclerView.ViewHolder(view) {

        private val binding = ListItemBinding.bind(view)
        var itemTemp: WeatherModel? = null
        init {
            itemView.setOnClickListener {
                itemTemp?.let { it1 -> listener?.onClick(it1) }
            }
        }
        fun bind(item: WeatherModel) = with(binding) {
            itemTemp = item
            tvDate.text = item.time
            tvCondition.text = item.condition
            if (item.currentTemp.isNotEmpty()) {
                tvTemp.text = "${item.currentTemp}°C"
            } else {
                tvTemp.text = "${item.maxTemp}°C / ${item.minTemp}°C"
            }
            //tvTemp.text = item.currentTemp.ifEmpty { "${item.maxTemp}°C / ${item.minTemp}°C" }
            Picasso.get().load("https:" + item.imageUrl).into(im)
        }
    }

    //Comparator ist eine innere Klasse, die verwendet wird, um Elemente im RecyclerView zu vergleichen und festzustellen, ob sie gleich sind.
    class Comparator : DiffUtil.ItemCallback<WeatherModel>(){
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

    }

    //onCreateViewHolder(parent: ViewGroup, viewType: Int) erstellt eine neue Instanz von Holder und gibt sie zurück.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view, listener)
    }

    //onBindViewHolder(holder: Holder, position: Int) bindet Daten an die Ansicht im RecyclerView.
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    //Listener ist eine Schnittstelle, die verwendet wird, um auf Klickereignisse in den RecyclerView-Elementen zu reagieren.
    interface Listener{
        fun onClick(item: WeatherModel)
    }
}