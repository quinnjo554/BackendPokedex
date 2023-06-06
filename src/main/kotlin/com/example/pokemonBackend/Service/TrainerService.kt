package com.example.pokemonBackend.Service

import com.example.pokemonBackend.Exceptions.PokeTrainerNotFoundException
import com.example.pokemonBackend.Exceptions.TrainerNotFoundException
import com.example.pokemonBackend.model.PokeTrainer
import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.model.Trainer
import com.example.pokemonBackend.repository.PokeTrainerRepo
import com.example.pokemonBackend.repository.PokemonRepo
import com.example.pokemonBackend.repository.TrainerRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable


@Service
class TrainerService(
        private val trainerRepo: TrainerRepo,
        private val pokeTrainerRepo: PokeTrainerRepo,
        private val pokemonRepository: PokemonRepo
) {
    private fun authenticate(email: String, password: String): Boolean {
        val trainer = trainerRepo.findByEmail(email)
        return trainer != null && trainer.password == password
    }
    fun signIn(trainer: Trainer): Pair<String,HttpStatus> {
        val email = trainer.email
        val password = trainer.password
        val authenticated = authenticate(email, password)
        return if (authenticated) {
            Pair("Authentication successful",HttpStatus.ACCEPTED)
        } else {
            Pair("Authentication failed",HttpStatus.UNAUTHORIZED)
        }
    }


    fun createTrainer(trainer: Trainer): String {
        val existingTrainer = trainerRepo.findByEmail(trainer.email)
        if (existingTrainer != null) {
            throw TrainerNotFoundException("Trainer already exist")
        }
        val savedTrainer = trainerRepo.save(trainer)
        return "Trainer created with ID: ${savedTrainer.trainerId}"
    }


    fun deleteTrainer(id: Long): String {
        val trainer = trainerRepo.findById(id)
        return if (trainer.isPresent) {
            trainerRepo.delete(trainer.get())
            "Trainer with id:${id} was deleted"
        } else {
            throw TrainerNotFoundException("Trainer with id:${id} was not found")
        }
    }

    fun deleteTrainerByEmail(email: String): String {
        val trainer = trainerRepo.findByEmail(email)
        return if(trainer != null) {
            trainerRepo.delete(trainer)
            "Trainer with email:${email} successfully deleted"
        }
        else{
            throw TrainerNotFoundException("Trainer with email:${email} was not found")
        }
    }


    fun getTrainerByID(id: Long): Trainer {
        return trainerRepo.findById(id).orElseThrow {
            throw TrainerNotFoundException("Trainer with id:${id} was not found")
        }
    }


    fun getTrainerByEmail(email: String): Trainer? {
        return trainerRepo.findByEmail(email) ?: throw TrainerNotFoundException("Trainer with email:${email} was not found")
    }

    fun getCapturedPokemon(
            id: Int,
            page: Int,
            size: Int,
            sortBy: String,
            sortOrder: String
    ): Page<Pokemon?> {
        val capturedPokemonIds: List<Int> = pokeTrainerRepo.findByTrainerId(id)
        if (capturedPokemonIds.isNullOrEmpty()) {
            throw TrainerNotFoundException("Trainer with id:${id} has no pokemon")
        }
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val capturedPokemonPage = pokemonRepository.findAllByIdIn(capturedPokemonIds, pageable)
        if (capturedPokemonPage.isEmpty) {
            throw TrainerNotFoundException("Could not get captured pokemon")
        }
        return capturedPokemonPage


    }



    fun releasePokemon(pokeId: Int, trainerId: Int): String {
        val pokeTrainer: PokeTrainer? = pokeTrainerRepo.findByTrainerIdAndPokeId(trainerId, pokeId) ?:throw PokeTrainerNotFoundException("PokeTrainer not Found")
        return if (pokeTrainer != null) {
            pokeTrainerRepo.deleteById(pokeTrainer.pokeTrainerid)
            "Deletion successful"
        } else {
            "PokeTrainer not found"
        }
    }
}
