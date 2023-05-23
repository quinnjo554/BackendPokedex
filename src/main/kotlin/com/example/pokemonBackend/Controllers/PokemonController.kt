package com.example.pokemonBackend.Controllers

import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.repository.PokemonRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.random.Random

@RestController
@RequestMapping("/pokemon")
class PokemonController(private val pokemonRepository: PokemonRepository) {

    //make a game where you guess what pokemon it is based on description

    @GetMapping("/{id}")
    fun getPokemon(@PathVariable id: Int):ResponseEntity<Pokemon?> {
        val pokemon = pokemonRepository.findById(id).orElse(null)
        return ResponseEntity(pokemon, HttpStatus.OK)
    }


    @GetMapping("/all")
    fun getAllPokemon(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") size: Int,
            @RequestParam(defaultValue = "id") sortBy: String,
            @RequestParam(defaultValue = "asc") sortOrder: String
    ): ResponseEntity<Page<Pokemon>> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val pokemonPage = pokemonRepository.findAll(pageable)
        return ResponseEntity(pokemonPage, HttpStatus.OK)
    }

    //get random pokemon
    @GetMapping("/random")
    fun getRandomPokemon(): ResponseEntity<Pokemon?> {
        val randomInt = Random.nextInt(1,553)
        val pokemon = pokemonRepository.findById(randomInt).orElse(null)
        return ResponseEntity(pokemon, HttpStatus.OK)
    }




}
