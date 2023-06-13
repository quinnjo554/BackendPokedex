package com.example.pokemonBackend.service

import com.example.pokemonBackend.exceptions.NotAuthorizedException
import com.example.pokemonBackend.exceptions.PokeTrainerNotFoundException
import com.example.pokemonBackend.exceptions.TrainerNotFoundException
import com.example.pokemonBackend.model.PokeTrainer
import com.example.pokemonBackend.model.Trainer
import com.example.pokemonBackend.repository.PokeTrainerRepo
import com.example.pokemonBackend.repository.TrainerRepo
import com.example.pokemonBackend.request.TrainerRequest
import org.springframework.stereotype.Service

@Service
class TrainerService(
        private val trainerRepo: TrainerRepo,
        private val pokeTrainerRepo: PokeTrainerRepo,
) {
    private fun authenticate(email: String, password: String): Boolean {
        val trainer = trainerRepo.findByEmail(email)
        return trainer != null && trainer.password == password
    }

    fun signIn(trainerRequest: TrainerRequest) {
        val email = trainerRequest.email
        val password = trainerRequest.password
        val authenticated = authenticate(email, password)
        if (!authenticated) {
            throw NotAuthorizedException()
        }
    }

    fun createTrainer(trainerRequest: TrainerRequest) {
        trainerRepo.findByEmail(trainerRequest.email) ?: throw (TrainerNotFoundException())
        val trainer = Trainer(email = trainerRequest.email, password = trainerRequest.password)
        trainerRepo.save(trainer)
    }

    fun deleteTrainer(id: Long) {
        val trainer = trainerRepo.findById(id)
        if (trainer.isPresent) {
            val trainerToDelete = trainer.get()
            trainerRepo.delete(trainerToDelete)
        } else {
            throw TrainerNotFoundException()
        }
    }

    fun deleteTrainerByEmail(email: String): Trainer {
        val trainer = trainerRepo.findByEmail(email)
        if (trainer != null) {
            trainerRepo.delete(trainer)
            return trainer
        } else {
            throw TrainerNotFoundException()
        }
    }

    fun getTrainerByID(id: Long): Trainer {
        return trainerRepo.findById(id).orElseThrow {
            throw TrainerNotFoundException()
        }
    }

    fun getTrainerByEmail(email: String): Trainer? {
        return trainerRepo.findByEmail(email) ?: throw TrainerNotFoundException()
    }

    fun releasePokemon(pokeId: Int, trainerId: Int) {
        val pokeTrainer: PokeTrainer? = pokeTrainerRepo.findByTrainerIdAndPokeId(trainerId, pokeId)
                ?: throw PokeTrainerNotFoundException()
        if (pokeTrainer != null) {
            pokeTrainerRepo.deleteById(pokeTrainer.id)
        }
    }
}
