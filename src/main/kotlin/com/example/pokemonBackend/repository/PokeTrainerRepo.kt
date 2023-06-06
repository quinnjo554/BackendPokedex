package com.example.pokemonBackend.repository

import com.example.pokemonBackend.model.PokeTrainer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface PokeTrainerRepo : JpaRepository<PokeTrainer, Int> {
        @Query("SELECT pt.pokeId FROM PokeTrainer pt WHERE pt.trainerId = :trainerId")
        fun findByTrainerId(@Param("trainerId") trainerId: Int): List<Int>

        @Query("SELECT pt FROM PokeTrainer pt WHERE pt.trainerId = :trainerId AND pt.pokeId = :pokeId")
        fun findByTrainerIdAndPokeId(@Param("trainerId") trainerId: Int, @Param("pokeId") pokeId: Int): PokeTrainer?


}
