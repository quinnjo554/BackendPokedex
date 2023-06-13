package com.example.pokemonBackend.controllers

import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.repository.*
import com.example.pokemonBackend.service.PokemonService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pokemon")
class PokemonController(private val pokemonService: PokemonService) {

    @GetMapping("/{id}")
    fun getPokemon(@PathVariable id: Int): ResponseEntity<Pokemon> {
        val pokemon = pokemonService.getPokemon(id)
        return ResponseEntity(pokemon, HttpStatus.OK)
    }

    @GetMapping("/name/{name}")
    fun getPokemonByName(
            @PathVariable name: String,
    ): ResponseEntity<Pokemon?> {
        val pokePage = pokemonService.getPokemonByName(name)
        return ResponseEntity(pokePage, HttpStatus.OK)
    }

    @GetMapping("/all")
    fun getAllPokemon(
            @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable
    ): ResponseEntity<Page<Pokemon>> {
        val pokemonPage = pokemonService.getAllPokemon(pageable)
        return ResponseEntity(pokemonPage, HttpStatus.OK)
    }

    @GetMapping("/by-type/{type}")
    fun getPokemonByType(
            @PathVariable type: String,
            @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable
    ): ResponseEntity<Page<Pokemon>> {
        val pokemonPage = pokemonService.getPokemonByType(type, pageable)
        return ResponseEntity(pokemonPage, HttpStatus.OK)
    }

    @GetMapping("/by-ability/{ability}")
    fun getPokemonByAbility(
            @PathVariable ability: String,
            @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable
    ): ResponseEntity<Page<Pokemon>> {
        val pokemonPage = pokemonService.getPokemonByAbility(ability, pageable)
        return ResponseEntity(pokemonPage, HttpStatus.OK)
    }

    @GetMapping("/by-egg-group/{eggGroup}")
    fun getPokemonByEggGroup(
            @PathVariable eggGroup: String,
            @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable
    ): ResponseEntity<Page<Pokemon>> {
        val pokemonPage = pokemonService.getPokemonByEggGroup(eggGroup, pageable)
        return ResponseEntity(pokemonPage, HttpStatus.OK)
    }

    @GetMapping("/random")
    fun getRandomPokemon(): ResponseEntity<Pokemon> {
        val pokemon = pokemonService.getRandomPokemon()
        return ResponseEntity(pokemon, HttpStatus.OK)
    }
}


