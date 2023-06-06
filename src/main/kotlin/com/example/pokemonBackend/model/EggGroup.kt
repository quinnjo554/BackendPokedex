package com.example.pokemonBackend.model

import jakarta.persistence.*
@Entity
@Table(name = "egg_group")
data class EggGroup(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "egg_group_id")
        var id: Int = 0,

        @Column(name = "egg_group_name")
        var name: String = ""
)