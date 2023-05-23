package com.example.pokemonBackend.repository

import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.model.Trainer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepository : JpaRepository<Pokemon, Int> {
    fun findAllByTypesContainingOrderByTypes(type: String): List<Pokemon>

}
