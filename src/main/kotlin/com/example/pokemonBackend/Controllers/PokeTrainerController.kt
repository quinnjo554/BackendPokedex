package com.example.pokemonBackend.Controllers

import com.example.pokemonBackend.model.PokeTrainer
import com.example.pokemonBackend.repository.PokeTrainerRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("poke_trainer")
class PokeTrainerController(private val pokeTrainerRepo: PokeTrainerRepo) {





}