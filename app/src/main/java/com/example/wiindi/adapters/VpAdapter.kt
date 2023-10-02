package com.example.wiindi.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

//VpAdapter ist eine Kotlin-Klasse, die von FragmentStateAdapter erbt. Sie wird verwendet, um Fragmente in einem ViewPager2 anzuzeigen.
//Der Konstruktor VpAdapter nimmt zwei Parameter entgegen: fragmentActivity, eine Instanz von FragmentActivity, und list, eine Liste von Fragmenten.

class VpAdapter(fragmentActivity: FragmentActivity, private val list: List<Fragment>): FragmentStateAdapter(fragmentActivity) {

    //Gibt die Anzahl der Fragmente in der Liste: list zurück.
    override fun getItemCount(): Int {
        return list.size
    }

    //Gibt das Fragment an der gegebenen Position in der Liste: list zurück.
    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}