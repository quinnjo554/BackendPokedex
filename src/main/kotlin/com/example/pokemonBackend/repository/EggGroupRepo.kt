package com.example.pokemonBackend.repository


import com.example.pokemonBackend.model.EggGroup
import org.springframework.data.jpa.repository.JpaRepository


interface EggGroupRepo :JpaRepository<EggGroup, Long> {
    fun findByName(name:String): EggGroup?

}
