package com.example.pokemonBackend.service

import com.example.pokemonBackend.exceptions.AlreadyExistsException
import com.example.pokemonBackend.exceptions.PokemonNotFoundException
import com.example.pokemonBackend.exceptions.TrainerNotFoundException
import com.example.pokemonBackend.model.PokeTrainer
import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.repository.PokeTrainerRepo
import com.example.pokemonBackend.repository.PokemonRepo
import com.example.pokemonBackend.repository.TrainerRepo
import com.example.pokemonBackend.request.PokeTrainerRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Service

@Service
class PokeTrainerService(private val pokeTrainerRepo: PokeTrainerRepo, private val trainerRepo: TrainerRepo, private val pokemonRepo: PokemonRepo) {
    fun addPokemonToTrainer(pokeTrainerRequest: PokeTrainerRequest) {
        val trainerId = pokeTrainerRequest.trainerId
        val pokeId = pokeTrainerRequest.pokeId

        trainerRepo.findById(trainerId.toLong()).orElseThrow {
            TrainerNotFoundException()
        }
        val existingPokemon = pokeTrainerRepo.findByTrainerIdAndPokeId(trainerId, pokeId)
        if (existingPokemon != null) {
            throw AlreadyExistsException("Pokemon already exists for the trainer")
        }
        val pokeTrainer = PokeTrainer(trainerId = pokeTrainerRequest.trainerId, pokeId = pokeTrainerRequest.pokeId)
        pokeTrainerRepo.save(pokeTrainer)
    }

    fun getCapturedPokemon(
            id: Int,
            @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable,
    ): Page<Pokemon> {
        val capturedPokemonIds: List<Int> = pokeTrainerRepo.findByTrainerId(id)
        if (capturedPokemonIds.isEmpty()) {
            throw TrainerNotFoundException()
        }

        val capturedPokemonPage = pokemonRepo.findAllByIdIn(capturedPokemonIds, pageable)
        if (capturedPokemonPage.isEmpty) {
            throw PokemonNotFoundException("Could not get pokemon")
        }
        return capturedPokemonPage
    }
}
