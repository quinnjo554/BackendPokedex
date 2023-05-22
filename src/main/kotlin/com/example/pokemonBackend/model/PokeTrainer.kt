package com.example.pokemonBackend.model

import jakarta.persistence.*

@Entity
@Table(name="poke_trainer")
data class PokeTrainer (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="poke_trainer_id")
        val pokeTrainerid:Int = 0,
        @Column(name="poke_id")
        val pokeId: Int = 0,
        @Column(name="trainer_id")
        val trainerId: Int = 0,
)