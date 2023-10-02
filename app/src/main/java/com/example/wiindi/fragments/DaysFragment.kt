package com.example.wiindi.fragments

import android.os.Bundle
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
import com.example.wiindi.databinding.FragmentDaysBinding

//Ein Fragment, das eine Liste von Wetterdaten für Tage anzeigt.
class DaysFragment : Fragment(), WeatherAdapter.Listener {
    private lateinit var adapter: WeatherAdapter
    private lateinit var binding: FragmentDaysBinding
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Initialisiert den RecyclerView und den Adapter.
    private fun init() = with(binding) {
        adapter = WeatherAdapter(this@DaysFragment)
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
    }

    //wird aufgerufen, wenn das Fragment erstellt wurde. Hier wird init() aufgerufen und die LiveData des ViewModels beobachtet,
    // um die Daten in den RecyclerView zu laden.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.liveDataList.observe(viewLifecycleOwner) {
            adapter.submitList(it.subList(1, it.size))
        }
    }

    //Erstelle und gebe eine neue Instanz der DaysFragment-Klasse zurück.
    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    //Methode, die aufgerufen wird, wenn ein Element im RecyclerView geklickt wird.
    override fun onClick(item: WeatherModel) {
        model.liveDataCurrent.value = item
    }
}