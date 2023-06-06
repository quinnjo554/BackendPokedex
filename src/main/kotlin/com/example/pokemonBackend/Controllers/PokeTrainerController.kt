package com.example.pokemonBackend.Controllers


import com.example.pokemonBackend.Exceptions.AlreadyExistsException
import com.example.pokemonBackend.Exceptions.OperationFailedException
import com.example.pokemonBackend.Exceptions.TrainerNotFoundException
import com.example.pokemonBackend.Service.PokeTrainerService
import com.example.pokemonBackend.Service.TrainerService
import com.example.pokemonBackend.model.PokeTrainer
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("poke_trainer")
class PokeTrainerController( private val pokeTrainerService: PokeTrainerService) {

    //add the anything with poketrainer repo to this
    @PostMapping("/post/pokemonToTrainer")
    fun addPokemonToTrainer(@RequestBody pokeTrainer: PokeTrainer): ResponseEntity<String> {
        return try {
            val response = pokeTrainerService.addPokemonToTrainer(pokeTrainer)
            return ResponseEntity(response, HttpStatus.OK)
        }
        catch (exception: AlreadyExistsException){
            return ResponseEntity("Pokemon already exist", HttpStatus.BAD_REQUEST)
        }
        catch (exception: OperationFailedException){
            return ResponseEntity("Failed to add pokemon to trainer", HttpStatus.BAD_REQUEST)
        }
        catch (exception: TrainerNotFoundException){
            return ResponseEntity("Trainer Not Found", HttpStatus.NOT_FOUND)
        }
    }

}