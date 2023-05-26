package com.example.pokemonBackend.repository

import com.example.pokemonBackend.model.Trainer
import com.example.pokemonBackend.model.Type
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeRepo : JpaRepository<Type, Long> {
    fun findByName(name:String):Type?
}