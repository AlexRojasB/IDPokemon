package com.alexrojasb.idpokemon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginRight
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.alexrojasb.idpokemon.R
import com.alexrojasb.idpokemon.databinding.FragmentDetailPokemonBinding
import com.alexrojasb.idpokemon.databinding.FragmentListPokemonBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_pokemon.*
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams


class PokemonDetailFragment : Fragment() {
    private var _binding: FragmentDetailPokemonBinding? = null
    private val binding get () = _binding!!
    private val args: PokemonDetailFragmentArgs by navArgs()
    private lateinit var pokemonNameTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemonDetailName.text = args.pokemon.name
        Picasso.get()
             .load(args.pokemon.sprites?.front_default)
           .into(binding.pokemonImage)
        args.pokemon.types?.forEach {
            var typeTxt = TextView(context)
            typeTxt.textSize = 12f
            val params: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, 5, 0)
            typeTxt.layoutParams = params
            typeTxt.text = it.type.name

            binding.typesRow.addView(typeTxt)
        }
    }
}