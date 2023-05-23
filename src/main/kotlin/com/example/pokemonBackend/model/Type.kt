
package com.example.pokemonBackend.model

import jakarta.persistence.*

@Entity
@Table(name = "types")
data class Type(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "type_id")
        var id: Int = 0,

        @Column(name = "type_name")
        var name: String = "",
)