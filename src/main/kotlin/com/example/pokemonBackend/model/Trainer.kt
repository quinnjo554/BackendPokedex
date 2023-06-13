package com.example.pokemonBackend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "trainers")
data class Trainer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    val id: Int = 0,
    @Column(name = "name")
    val name: String = "",
    @Column(name = "email")
    val email: String = "",
    @Column(name = "password")
    val password: String = "",
)
