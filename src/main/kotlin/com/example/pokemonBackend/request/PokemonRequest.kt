package com.example.pokemonBackend.request

import com.example.pokemonBackend.model.Ability
import com.example.pokemonBackend.model.EggGroup
import com.example.pokemonBackend.model.Type

data class PokemonRequest(
    var name: String,
    var height: Double,
    var weight: Double,
    var hp: Int,
    var speed: Int,
    var attack: Int,
    var defense: Int,
    var specialAttack: Int,
    var specialDefense: Int,
    var genus: String,
    var description: String = "",
    var abilities: Set<Ability> = emptySet(),
    var types: Set<Type> = emptySet(),
    var eggGroups: Set<EggGroup> = emptySet(),
)
