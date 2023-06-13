package com.example.pokemonBackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokemonBackendApplication

fun main(args: Array<String>) {
    runApplication<PokemonBackendApplication>(*args)
}
