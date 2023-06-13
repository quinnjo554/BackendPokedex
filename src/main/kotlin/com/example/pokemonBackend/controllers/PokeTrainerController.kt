package com.example.pokemonBackend.controllers

import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.request.PokeTrainerRequest
import com.example.pokemonBackend.service.PokeTrainerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("poke-trainer")
class PokeTrainerController(private val pokeTrainerService: PokeTrainerService, private val trainerService: PokeTrainerService) {

    @PostMapping("/post/pokemon-to-trainer")
    fun addPokemonToTrainer(@RequestBody pokeTrainerRequest: PokeTrainerRequest) {
        return pokeTrainerService.addPokemonToTrainer(pokeTrainerRequest)
    }

    @GetMapping("/{id}/captured-pokemon")
    fun getCapturedPokemon(
            @PathVariable id: Int,
            @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable
    ): ResponseEntity<Page<Pokemon>> {
        val responsePage = trainerService.getCapturedPokemon(id, pageable)
        return ResponseEntity(responsePage, HttpStatus.OK)
    }

}