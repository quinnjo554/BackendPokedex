package com.example.pokemonBackend.model

import jakarta.persistence.*

@Entity
@Table(name = "pokemon")
data class Pokemon(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "poke_id")
        var id: Int = 0,

        @Column(name = "poke_name")
        var name: String = "",

        @Column(name = "poke_type")
        var types: String = "",

        @Column(name = "height")
        var height: String = "",

        @Column(name = "weight")
        var weight: String = "",

        @Column(name = "abilities")
        var abilities: String = "",

        @Column(name = "eggGroups")
        var eggGroups: String = "",

        @Column(name = "hp")
        var hp: Int = 0,

        @Column(name = "speed")
        var speed: Int = 0,

        @Column(name = "attack")
        var attack: Int = 0,

        @Column(name = "defense")
        var defense: Int = 0,

        @Column(name = "special_attack")
        var specialAttack: Int = 0,

        @Column(name = "special_defense")
        var specialDefense: Int = 0,

        @Column(name = "genus")
        var genus: String = "",

        @Column(name = "description")
        var description: String = "",

)