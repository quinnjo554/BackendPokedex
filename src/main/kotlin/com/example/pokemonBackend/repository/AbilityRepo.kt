package com.example.pokemonBackend.repository

import com.example.pokemonBackend.model.Ability
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AbilityRepo : JpaRepository<Ability, Long> {
    fun findByName(name: String): Ability?
}
