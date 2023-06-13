package com.example.pokemonBackend.repository

import com.example.pokemonBackend.model.Ability
import com.example.pokemonBackend.model.EggGroup
import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.model.Type
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepo : JpaRepository<Pokemon, Int> {
    fun findAllByTypesContainingOrderByTypes(type: Type?, pageable: Pageable): Page<Pokemon>
    fun findAllByAbilitiesContainingOrderByAbilities(ability: Ability?, pageable: Pageable): Page<Pokemon>
    fun findByName(name: String): Pokemon?
    fun findAllByEggGroupsContainingOrderByEggGroups(eggGroup: EggGroup?, pageable: Pageable): Page<Pokemon>
    fun findAllByIdIn(ids: List<Int>, pageable: Pageable): Page<Pokemon>
}
