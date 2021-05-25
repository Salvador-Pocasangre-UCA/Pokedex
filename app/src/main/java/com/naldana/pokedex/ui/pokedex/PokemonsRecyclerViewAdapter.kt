package com.naldana.pokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.naldana.pokedex.R
import com.naldana.pokedex.data.entity.Pokemon
import com.naldana.pokedex.data.entity.PokemonType
import com.naldana.pokedex.data.entity.PokemonWithType
import java.util.*

class PokemonsRecyclerViewAdapter :
    RecyclerView.Adapter<PokemonsRecyclerViewAdapter.PokemonViewHolder>() {

    private var pokemons: List<PokemonWithType>? = null

    fun setData(data: List<PokemonWithType>) {
        this.pokemons = data
        notifyDataSetChanged()
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: PokemonWithType) {
            val idTextView = itemView.findViewById<TextView>(R.id.idPokemon)
            val nameTextView = itemView.findViewById<TextView>(R.id.namePokemon)
            val imageView = itemView.findViewById<ImageView>(R.id.image_pokemon)
            setTypes(data.types)
            idTextView.text = data.pokemon.id.toString()
            nameTextView.text = data.pokemon.name.capitalize(Locale.ROOT)
            Glide.with(itemView)
                .load(data.pokemon.sprites.frontDefault)
                .centerCrop()
                .placeholder(R.drawable.pokebola)
                .into(imageView)
        }

        private fun setTypes(types: List<PokemonType>){
            val chips = itemView.findViewById<ChipGroup>(R.id.pokemon_types)
            chips.removeAllViews()
            types.forEach{
                chips.addView(Chip(itemView.context).apply {
                    text = it.type.name.capitalize(Locale.getDefault())
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val data = pokemons ?: return
        val pokemon = data[position]
        holder.bind(pokemon)

    }

    override fun getItemCount(): Int = pokemons?.size ?: 0
}