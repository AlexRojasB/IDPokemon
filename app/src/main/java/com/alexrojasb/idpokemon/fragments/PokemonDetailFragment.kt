package com.alexrojasb.idpokemon.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.alexrojasb.idpokemon.R

class PokemonDetailFragment : Fragment(R.layout.fragment_detail_pokemon) {
    private val args: PokemonDetailFragmentArgs by navArgs()
    private lateinit var pokemonNameTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonNameTextView = view.findViewById(R.id.pokemonDetailName)
        pokemonNameTextView.text = args.pokemon.pokemonName
    }
}