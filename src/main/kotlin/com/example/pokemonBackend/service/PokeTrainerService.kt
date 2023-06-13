package com.example.pokemonBackend.Service

import com.example.pokemonBackend.Exceptions.AlreadyExistsException
import com.example.pokemonBackend.Exceptions.OperationFailedException
import com.example.pokemonBackend.Exceptions.TrainerNotFoundException
import com.example.pokemonBackend.model.PokeTrainer
import com.example.pokemonBackend.repository.PokeTrainerRepo
import com.example.pokemonBackend.repository.TrainerRepo
import org.springframework.stereotype.Service
@Service
class PokeTrainerService(private val pokeTrainerRepo: PokeTrainerRepo, private val trainerRepo: TrainerRepo) {
    fun addPokemonToTrainer(pokeTrainer: PokeTrainer): String {
        val trainerId = pokeTrainer.trainerId
        val pokeId = pokeTrainer.pokeId

        val trainer = trainerRepo.findById(trainerId.toLong()).orElseThrow {
            TrainerNotFoundException("Trainer not found with ID: $trainerId")
        }

        val existingPokemon = pokeTrainerRepo.findByTrainerIdAndPokeId(trainerId, pokeId)
        if (existingPokemon != null) {
            throw AlreadyExistsException("Pokemon already exists for the trainer")
        }

        val savedPokeTrainer = pokeTrainerRepo.save(pokeTrainer)
        return if (savedPokeTrainer != null) {
            "Pokemon added to trainer successfully"
        } else {
            throw OperationFailedException("Failed to add pokemon to trainer")
        }
    }
}

