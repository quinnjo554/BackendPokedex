package com.example.pokemonBackend.repository

import com.example.pokemonBackend.model.Abillity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface AbilityRepo : JpaRepository<Abillity, Long> {
    fun findByName(name:String):Abillity?
}
