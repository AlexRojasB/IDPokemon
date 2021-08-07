package com.alexrojasb.idpokemon.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alexrojasb.idpokemon.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment(R.layout.fragment_main) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val navHostFragment = childFragmentManager.findFragmentById(R.id.inner_nav_host_fragment) as NavHostFragment
        val innerBottomNavigationView = view.findViewById<BottomNavigationView>(R.id.innerBottomNavigationView)
        val navController = navHostFragment.navController
        innerBottomNavigationView.setupWithNavController(navController)
    }

}