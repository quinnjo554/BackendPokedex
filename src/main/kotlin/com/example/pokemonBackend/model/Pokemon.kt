package com.example.pokemonBackend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon")
data class Pokemon(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "poke_id")
        var id: Int,

        @Column(name = "poke_name")
        var name: String,

        @Column(name = "height")
        var height: Double,

        @Column(name = "weight")
        var weight: Double,

        @Column(name = "hp")
        var hp: Int,

        @Column(name = "speed")
        var speed: Int,

        @Column(name = "attack")
        var attack: Int,

        @Column(name = "defense")
        var defense: Int,

        @Column(name = "special_attack")
        var specialAttack: Int,

        @Column(name = "special_defense")
        var specialDefense: Int,

        @Column(name = "genus")
        var genus: String,

        @Column(name = "description")
        var description: String = "",

        @ManyToMany(fetch = FetchType.EAGER) // load with rest of the fields
        @JoinTable(
                name = "pokemon_ability",
                joinColumns = [JoinColumn(name = "pokemon_id")],
                inverseJoinColumns = [JoinColumn(name = "ability_id")],
        )
        var abilities: Set<Ability> = emptySet(),

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "pokemon_type",
                joinColumns = [JoinColumn(name = "pokemon_id")],
                inverseJoinColumns = [JoinColumn(name = "type_id")],
        )
        var types: Set<Type> = emptySet(),

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "pokemon_egg_group",
                joinColumns = [JoinColumn(name = "pokemon_id")],
                inverseJoinColumns = [JoinColumn(name = "egg_group_id")],
        )
        var eggGroups: Set<EggGroup> = emptySet(),

        )
