package com.example.pokemonBackend.model

import jakarta.persistence.*

@Entity
@Table(name = "ability")
data class Abillity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ability_id")
        var id: Int = 0,

        @Column(name = "ability_name")
        var name: String = ""
)
