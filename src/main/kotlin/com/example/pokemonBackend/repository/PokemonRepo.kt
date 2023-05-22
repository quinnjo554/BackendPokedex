package com.example.pokemonBackend.repository

import com.example.pokemonBackend.model.Pokemon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepository : JpaRepository<Pokemon, Int> {


}
