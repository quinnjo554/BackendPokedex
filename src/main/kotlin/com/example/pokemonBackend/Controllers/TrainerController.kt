package com.example.pokemonBackend.Controllers

import com.example.pokemonBackend.model.PokeTrainer
import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.model.Trainer
import com.example.pokemonBackend.repository.PokeTrainerRepo
import com.example.pokemonBackend.repository.PokemonRepository
import com.example.pokemonBackend.repository.TrainerRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

//chat gpt for questions on the pokemon


@RestController
@RequestMapping("/trainer")
class TrainerController(private val trainerRepo: TrainerRepo,private val pokeTrainerRepo: PokeTrainerRepo,private val pokemonRepository: PokemonRepository) {
    /**TODO
     * add to get repo
     * add trainer table and get that set up
     * check requirements
     */

    //add trainer to db
    @PostMapping("/post")
    fun createTrainer(@RequestBody trainer: Trainer): ResponseEntity<String> {
        //get the email
        val existingTrainer = trainerRepo.findByEmail(trainer.email)
        return if (existingTrainer != null) {
            //error if email is already in the data base
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists")
        } else {
            val savedTrainer = trainerRepo.save(trainer)
            ResponseEntity.ok("Trainer created with ID: ${savedTrainer.trainerId}")
        }
    }

    //delete a trainer by id
    @DeleteMapping("/delete/{id}")
    fun deleteTrainer(@PathVariable id: Long): ResponseEntity<Unit> {
        val trainer = trainerRepo.findById(id)
        if (trainer.isPresent) {
            //delete if exist
            trainerRepo.delete(trainer.get())
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.notFound().build()
    }

    //get a specific trainer
    @GetMapping("/{id}")
    fun getTrainerByID(@PathVariable id: Long): ResponseEntity<Trainer?> {
        val trainer = trainerRepo.findById(id).orElse(null)
        return ResponseEntity(trainer, HttpStatus.OK)
    }


    //get all trainers
    @GetMapping("/all")
    fun getTrainer(): ResponseEntity<List<Trainer>> {
        val trainerList = trainerRepo.findAll()
        return ResponseEntity(trainerList, HttpStatus.OK)
    }



    //get info about a specific pokemon
    @GetMapping("/{id}/captured-pokemon")
    fun getCapturedPokemon(@PathVariable id: Int): ResponseEntity<List<Pokemon>> {
        val capturedPokemonIds: List<Int> = pokeTrainerRepo.findByTrainerId(id)
        val capturedPokemon: List<Pokemon> = pokemonRepository.findAllById(capturedPokemonIds)
        return ResponseEntity.ok(capturedPokemon)
    }

    //add captured pokemon
    @PostMapping("/post/pokemonToTrainer")
    fun addPokemonToTrainer(@RequestBody pokeTrainer: PokeTrainer): ResponseEntity<String> {
        val trainerId = pokeTrainer.trainerId
        val pokeId = pokeTrainer.pokeId

        // Check if the trainer already has the same poke_id
        val existingPokemon = pokeTrainerRepo.findByTrainerIdAndPokeId(trainerId, pokeId)
        if (existingPokemon != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Trainer already has the same pokemon.")
        }
        val savedPokeTrainer = pokeTrainerRepo.save(pokeTrainer)
        return if (savedPokeTrainer != null) {
            ResponseEntity.ok("Pokemon added to trainer successfully.")
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add pokemon to trainer.")
        }
    }

    //uncapture a pokemon
    @DeleteMapping("/delete/{pokeId}/fromTrainer/{trainerId}")
    fun deletePokemonToTrainer(
            @PathVariable pokeId: Int,
            @PathVariable trainerId: Int
    ): ResponseEntity<String> {
        val pokeTrainer: PokeTrainer? = pokeTrainerRepo.findByTrainerIdAndPokeId(trainerId, pokeId)
        return if (pokeTrainer != null) {
            pokeTrainerRepo.deleteById(pokeTrainer.pokeTrainerid)
            ResponseEntity.ok("Deletion successful")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("PokeTrainer not found")
        }
    }
    
}
