package com.alexrojasb.idpokemon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexrojasb.idpokemon.R
import com.alexrojasb.idpokemon.models.Pokemon

class PokemonListAdapter(val onItemClicked: ((Pokemon) -> (Unit))? = null) : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {
    var pokemonList: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var pokemonNameTextView: TextView = itemView.findViewById(R.id.pokemonName)

        fun bind(model: Pokemon) {
            pokemonNameTextView.text = model.pokemonName
            itemView.setOnClickListener { onItemClicked?.invoke(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val holderView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_cell, parent,  false)
        return PokemonViewHolder(holderView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size
}