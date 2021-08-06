package com.alexrojasb.idpokemon.adapters


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter(val fragments: ArrayList<Fragment>, fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int  = fragments.count()

    override fun createFragment(position: Int): Fragment {
       return  fragments[position]
    }
}