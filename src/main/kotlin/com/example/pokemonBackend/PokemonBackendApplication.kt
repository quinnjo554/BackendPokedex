package com.example.pokemonBackend

import com.example.pokemonBackend.model.Pokemon
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class PokemonBackendApplication{

}

fun main(args: Array<String>) {
	runApplication<PokemonBackendApplication>(*args)
}
