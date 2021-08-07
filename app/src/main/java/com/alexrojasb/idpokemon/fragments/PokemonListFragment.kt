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
import com.alexrojasb.idpokemon.databinding.FragmentListPokemonBinding
import com.alexrojasb.idpokemon.databinding.FragmentPokemonsFavoriteBinding
import com.alexrojasb.idpokemon.models.Pokemon
import com.alexrojasb.idpokemon.viewModels.PokemonListViewModel
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PokemonListFragment : Fragment() {
    private var _binding: FragmentListPokemonBinding? = null
    private val binding get () = _binding!!
    private lateinit var viewModel: PokemonListViewModel
    private val disposable = CompositeDisposable()

    private  val adapter = PokemonListAdapter { pokemon ->
      if (pokemon.id == null) {
          viewModel.fetchOnePokemon(pokemon.name)
      } else {
          navigateToDetails(pokemon)
      }
    }

    private fun navigateToDetails(pokemon: Pokemon) {
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
        _binding = FragmentListPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pokemonRecycleView.adapter = adapter
        binding.pokemonRecycleView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        viewModel.pokemonList.observe(viewLifecycleOwner) {
            adapter.pokemonList = it
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.serverError.observe(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(view, "Something was wrong", Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.pokemonUpdateDetails.observe(viewLifecycleOwner) {
            navigateToDetails(it)
        }

        disposable.add(
            binding.exploreBtn.clicks()
                .subscribe{
                  viewModel.fetchPokemonList()
                }
        )
        disposable.add(
            binding.randomCatchBtn.clicks()
                .subscribe {
                    viewModel.fetchOneRandomPokemon()
                }
        )
    }

    override fun onDestroy() {
        disposable.clear()
        _binding = null
        super.onDestroy()
    }

}