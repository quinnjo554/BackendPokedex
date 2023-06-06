package com.example.pokemonBackend.repository
import com.example.pokemonBackend.model.Trainer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainerRepo : JpaRepository<Trainer,Long>{
    fun findByEmail(email: String): Trainer?
}