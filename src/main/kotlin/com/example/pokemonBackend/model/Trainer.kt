package com.example.pokemonBackend.model

import jakarta.persistence.*

@Entity
@Table(name="trainer")
data class Trainer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="trainer_id")
        val trainerId: Int = 0,
        @Column(name = "name")
        val names: String = "",
        @Column(name="email")
        val email: String = "",
        @Column(name="password")
        val password: String = ""
)
