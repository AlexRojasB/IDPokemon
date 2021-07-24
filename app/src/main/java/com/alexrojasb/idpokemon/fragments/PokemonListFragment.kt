package com.alexrojasb.idpokemon.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.alexrojasb.idpokemon.R
import com.alexrojasb.idpokemon.adapters.PokemonListAdapter
import com.alexrojasb.idpokemon.models.Pokemon

class PokemonListFragment : Fragment(R.layout.fragment_list_pokemon) {
private lateinit var  pokemonRecyclerView: RecyclerView
private  val adapter = PokemonListAdapter { pokemon ->
    val action =
        PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(pokemon)
    findNavController().navigate(action)
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonRecyclerView = view.findViewById(R.id.pokemonRecycleView)
        adapter.pokemonList = getDummyPokemonList()
        pokemonRecyclerView.adapter = adapter
        pokemonRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))

    }

    private fun getDummyPokemonList() : List<Pokemon> {
        return listOf(
            Pokemon("Pikachu"),
            Pokemon("Charmander"),
            Pokemon("Charizar"),
            Pokemon("Electabuzz"),
            Pokemon("Cindaquil"),
            Pokemon("Zigzagoon"),
            Pokemon("Magikar"),
            Pokemon("Articuno"),
            Pokemon("Zapdos")

        )
    }
}