package com.example.pokemonBackend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "poke_trainers")
data class PokeTrainer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "poke_trainer_id")
        val id: Int = 0,
        @Column(name = "poke_id")
        val pokeId: Int = 0,
        @Column(name = "trainer_id")
        val trainerId: Int = 0,
)
