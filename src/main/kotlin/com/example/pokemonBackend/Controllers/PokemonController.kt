package com.example.pokemonBackend.Controllers

import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.repository.PokemonRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pokemon")
class PokemonController(private val pokemonRepository: PokemonRepository) {


    @GetMapping("/{id}")
    fun getPokemon(@PathVariable id: Int):ResponseEntity<Pokemon?> {
        val pokemon = pokemonRepository.findById(id).orElse(null)
        return ResponseEntity(pokemon, HttpStatus.OK)
    }

    @GetMapping("/all")
    fun getAllPokemon(): ResponseEntity<List<Pokemon>> {
        val pokemonList = pokemonRepository.findAll()
        return ResponseEntity(pokemonList, HttpStatus.OK)
    }

}
