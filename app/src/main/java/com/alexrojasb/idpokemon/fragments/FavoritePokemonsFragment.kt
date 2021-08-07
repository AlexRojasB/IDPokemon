package com.alexrojasb.idpokemon.fragments

import android.content.AbstractThreadedSyncAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alexrojasb.idpokemon.R
import com.alexrojasb.idpokemon.adapters.PokemonListAdapter
import com.alexrojasb.idpokemon.databinding.FragmentPokemonsFavoriteBinding
import com.alexrojasb.idpokemon.viewModels.FavoriteViewModel

class FavoritePokemonsFragment: Fragment() {
    private var _binding: FragmentPokemonsFavoriteBinding? = null
    private val binding get () = _binding!!

    private lateinit var viewModel: FavoriteViewModel

    private val adapter = PokemonListAdapter {
        viewModel.delete(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonsFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoriteRecycleView.adapter = adapter
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}