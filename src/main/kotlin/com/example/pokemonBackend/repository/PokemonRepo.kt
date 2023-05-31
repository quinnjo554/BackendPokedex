package com.example.pokemonBackend.repository

import com.example.pokemonBackend.model.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepository : JpaRepository<Pokemon, Int> {
    fun findAllByTypesContainingOrderByTypes(type: Type?,pageable: Pageable): Page<Pokemon?>
    fun findAllByAbilitiesContainingOrderByAbilities(ability: Abillity?,pageable: Pageable): Page<Pokemon?>
    fun findByName(name:String,pageable: Pageable) : Page<Pokemon?>
    fun findAllByEggGroupsContainingOrderByEggGroups(eggGroup: EggGroup?,pageable: Pageable): Page<Pokemon?>
    fun findAllByIdIn(ids:List<Int>,pageable:Pageable): Page<Pokemon>

}
