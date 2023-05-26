package com.example.pokemonBackend.repository

import com.example.pokemonBackend.model.Abillity
import com.example.pokemonBackend.model.EggGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface EggGroupRepo :JpaRepository<EggGroup, Long> {
    fun findByName(name:String): EggGroup?

}
