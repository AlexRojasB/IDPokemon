package com.alexrojasb.idpokemon.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.alexrojasb.idpokemon.R
import com.alexrojasb.idpokemon.adapters.HomePagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {
    private lateinit var homepagerPageChanged: ViewPager2.OnPageChangeCallback
    private lateinit var homePager: ViewPager2
    private val backStack = Stack<Int>()
private val fragments = listOf<BaseFragment>(
    BaseFragment.newInstance(R.layout.content_pokemon_list_base, R.id.toolbar_home, R.id.nav_graph),
    BaseFragment.newInstance(R.layout.content_favorite_base, R.id.toolbar_favorite, R.id.favorite_nav_graph)
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val calback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        }
       requireActivity().onBackPressedDispatcher.addCallback(calback)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     /*   val navHostFragment = childFragmentManager.findFragmentById(R.id.inner_nav_host_fragment) as NavHostFragment
        val innerBottomNavigationView = view.findViewById<BottomNavigationView>(R.id.innerBottomNavigationView)
        val navController = navHostFragment.navController
        innerBottomNavigationView.setupWithNavController(navController)*/
        val homeAdapter = HomePagerAdapter(fragments, this )
        homePager = view.findViewById<ViewPager2>(R.id.homePager)
        val innerBottomNavigationView = view.findViewById<BottomNavigationView>(R.id.innerBottomNavigationView)
        homePager.adapter = homeAdapter
        homePager.offscreenPageLimit = fragments.size
        innerBottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.pokemonListFragment -> {
                    homePager.currentItem = 0
                }
                R.id.favoritePokemonsFragment -> {
                    homePager.currentItem = 1
                }
            }
            true
        }

        homepagerPageChanged = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                true.also { innerBottomNavigationView.menu.getItem(position).isChecked = it }
            }
        }

        homePager.registerOnPageChangeCallback(homepagerPageChanged)

    }


    override fun onDestroy() {
        homePager.unregisterOnPageChangeCallback(homepagerPageChanged)
        super.onDestroy()
    }

}