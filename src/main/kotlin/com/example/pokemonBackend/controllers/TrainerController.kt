package com.example.pokemonBackend.Controllers

import com.example.pokemonBackend.Exceptions.PokeTrainerNotFoundException
import com.example.pokemonBackend.Exceptions.TrainerNotFoundException
import com.example.pokemonBackend.Service.TrainerService
import com.example.pokemonBackend.model.PokeTrainer
import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.model.Trainer
import com.example.pokemonBackend.repository.PokeTrainerRepo
import com.example.pokemonBackend.repository.PokemonRepo
import com.example.pokemonBackend.repository.TrainerRepo
import org.apache.coyote.Response
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
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
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/trainer")
class TrainerController(private val trainerService: TrainerService) {

//Go through all after delete because there were errors in a few.

    @PostMapping("/login")
    fun signIn(@RequestBody trainer: Trainer): ResponseEntity<String> {
        val (authenticationMessage,authenticationStatus) = trainerService.signIn(trainer);
        return ResponseEntity(authenticationMessage,authenticationStatus)
    }

    //Doesnt work
    @PostMapping("/post")
    fun createTrainer(@RequestBody trainer: Trainer): ResponseEntity<String> {
        return try {
            val responseMessage = trainerService.createTrainer(trainer)
            ResponseEntity(responseMessage,HttpStatus.OK)
        } catch (exception: TrainerNotFoundException){ //Query didnt return a unique result. Had to use Exception
            ResponseEntity("Bad",HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteTrainer(@PathVariable id: Long): ResponseEntity<String> {
        return try {
            val responseMessage = trainerService.deleteTrainer(id)
            ResponseEntity(responseMessage, HttpStatus.OK)
        }
        catch (exception: TrainerNotFoundException) {
            ResponseEntity("Trainer with id:${id} not found", HttpStatus.NOT_FOUND)
        }
    }


    @DeleteMapping("/deleteByEmail")
    fun deleteTrainerByEmail(@RequestParam email: String): ResponseEntity<String> {
        return try {
            val responseMessage = trainerService.deleteTrainerByEmail(email)
            ResponseEntity(responseMessage,HttpStatus.OK)
        }
        catch (exception:TrainerNotFoundException){
            ResponseEntity("Trainer with email:${email} was not found",HttpStatus.NOT_FOUND)
        }

    }

    @GetMapping("/{id}")
    fun getTrainerByID(@PathVariable id: Long): ResponseEntity<Trainer?> {
        return try {
            val trainer = trainerService.getTrainerByID(id)
            ResponseEntity(trainer, HttpStatus.OK)
        } catch (exception: TrainerNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(Trainer())
        }
    }

    @GetMapping("/email")
    fun getTrainerByEmail(@RequestParam email: String): ResponseEntity<Trainer?> {
        return try{
            val trainer = trainerService.getTrainerByEmail(email)
             ResponseEntity(trainer,HttpStatus.OK)
        }
        catch (exception:TrainerNotFoundException){
            ResponseEntity(Trainer(),HttpStatus.NOT_FOUND)
        }

    }

    @GetMapping("/{id}/captured-pokemon")
    fun getCapturedPokemon(
            @PathVariable id: Int,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "553") size: Int,
            @RequestParam(defaultValue = "id") sortBy: String,
            @RequestParam(defaultValue = "asc") sortOrder: String
    ): ResponseEntity<Page<Pokemon?>> {
        return try {
            val responsePage = trainerService.getCapturedPokemon(id, page, size, sortBy, sortOrder)
            ResponseEntity(responsePage,HttpStatus.OK)
        }
        catch (exception:TrainerNotFoundException){
            ResponseEntity(Page.empty(),HttpStatus.NOT_FOUND)
        }
    }


    @DeleteMapping("/delete/{pokeId}/fromTrainer/{trainerId}")
    fun releasePokemon(
            @PathVariable pokeId: Int,
            @PathVariable trainerId: Int
    ): ResponseEntity<String> {
        return try {
            val response = trainerService.releasePokemon(pokeId, trainerId)
            ResponseEntity(response,HttpStatus.OK)
        }
        catch (exception:PokeTrainerNotFoundException){
            ResponseEntity("Could Not Release Pokemon",HttpStatus.NOT_FOUND)
        }
    }

}
