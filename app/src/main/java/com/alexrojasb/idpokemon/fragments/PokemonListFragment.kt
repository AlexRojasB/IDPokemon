package com.alexrojasb.idpokemon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.alexrojasb.idpokemon.R
import com.alexrojasb.idpokemon.adapters.PokemonListAdapter
import com.alexrojasb.idpokemon.models.Pokemon
import com.alexrojasb.idpokemon.viewModels.PokemonListViewModel
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PokemonListFragment : Fragment() {
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var  pokemonRecyclerView: RecyclerView
    private lateinit var pokemonImage: ImageView
    private lateinit var explore: Button
    private val disposable = CompositeDisposable()
    private lateinit var  progressBar: ProgressBar

    private  val adapter = PokemonListAdapter { pokemon ->
        val action =
            PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(pokemon)
        findNavController().navigate(action)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        explore = view.findViewById(R.id.exploreBtn)
        pokemonRecyclerView = view.findViewById(R.id.pokemonRecycleView)
        progressBar = view.findViewById(R.id.progressBar)

        pokemonRecyclerView.adapter = adapter
        pokemonRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        viewModel.pokemonList.observe(viewLifecycleOwner) {

            adapter.pokemonList = it
         //   Picasso.get()
           //     .load("")
             //   .into(pokemonImage)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.serverError.observe(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(view, "Secreto de amor, de un gran amor", Snackbar.LENGTH_LONG).show()
            }
        }

        disposable.add(
            explore.clicks()
                .subscribe{
                  viewModel.fetchPokemonList()
                }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val destination = when (item?.itemId) {
            R.id.favoritePokemonsFragment -> R.id.favoritePokemonsFragment
            else -> null
        }

        return if (destination != null) findNavController().navigate(destination).let { true }
        else super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}