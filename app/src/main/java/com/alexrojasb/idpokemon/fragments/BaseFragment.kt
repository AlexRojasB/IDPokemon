package com.alexrojasb.idpokemon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.alexrojasb.idpokemon.R

class BaseFragment : Fragment() {
    private val defaultInt = -1
    private var layoutRes: Int = -1
    private var toolbarId: Int = -1
    private var navHostId: Int = -1
    // root destinations
    private val rootDestinations =
        setOf(R.id.pokemonListFragment, R.id.favoritePokemonsFragment)
    // nav config with root destinations
    private val appBarConfig = AppBarConfiguration(rootDestinations)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // extract arguments from bundle
        arguments?.let {
            layoutRes = it.getInt(KEY_LAYOUT)
            toolbarId = it.getInt(KEY_TOOLBAR)
            navHostId = it.getInt(KEY_NAV_HOST)

        } ?: return
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return if (layoutRes == defaultInt) null
        else inflater.inflate(layoutRes, container, false)
    }

    override fun onStart() {
        super.onStart()
        // return early if no arguments were parsed
        if (toolbarId == defaultInt || navHostId == defaultInt) return
        // find navController using navHostFragment
        val navController = requireView().findNavController()
        // setup navigation with root destinations and toolbar
        val toolbar = requireView().findViewById<Toolbar>(toolbarId)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfig)
    }


    companion object {

        private const val KEY_TOOLBAR = "toolbar_key"
        private const val KEY_LAYOUT = "layout_key"
        private const val KEY_NAV_HOST = "nav_host_key"

        fun newInstance(layoutRes: Int, toolbarId: Int, navHostId: Int) = BaseFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_LAYOUT, layoutRes)
                putInt(KEY_NAV_HOST, navHostId)
                putInt(KEY_TOOLBAR, toolbarId)
            }
        }
    }
}