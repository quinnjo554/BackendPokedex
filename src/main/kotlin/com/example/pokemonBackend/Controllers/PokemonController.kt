package com.example.pokemonBackend.Controllers

import com.example.pokemonBackend.Exceptions.PokemonNotFoundException
import com.example.pokemonBackend.Service.PokemonService
import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.repository.*
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//at the next intern meet up ask about the future of programing
//also about grpc and protobuf and how they differ from rest api's

@RestController
@RequestMapping("/pokemon")
class PokemonController(private val pokemonService: PokemonService) {

    @GetMapping("/{id}")
    fun getPokemon(@PathVariable id: Int): ResponseEntity<Pokemon?> {
        try {
            val pokemon = pokemonService.getPokemon(id)
            return ResponseEntity(pokemon, HttpStatus.OK)
        }
        catch (exception:PokemonNotFoundException){
            ResponseEntity(Pokemon(),HttpStatus.NOT_FOUND)
        }
       return ResponseEntity(Pokemon(),HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/name/{name}")
    fun getPokemonByName(
        @PathVariable name: String,
    ): ResponseEntity<Pokemon?> {
        try {
             val pokePage =pokemonService.getPokemonByName(name)
            return ResponseEntity(pokePage,HttpStatus.OK)
        }
        catch(exception:PokemonNotFoundException){
            return ResponseEntity(Pokemon(),HttpStatus.NOT_FOUND)
        }

    }

    @GetMapping("/all")
    fun getAllPokemon(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "500") size: Int,
            @RequestParam(defaultValue = "id") sortBy: String,
            @RequestParam(defaultValue = "asc") sortOrder: String
    ): ResponseEntity<Page<Pokemon>> {
        return try {
            val pokemonPage = pokemonService.getAllPokemon(page, size, sortBy, sortOrder)
            ResponseEntity(pokemonPage, HttpStatus.OK)
        } catch (e: PokemonNotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/byType/{type}")
    fun getRandomPokemonByType(
            @PathVariable type: String,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "500") size: Int,
            @RequestParam(defaultValue = "id") sortBy: String,
            @RequestParam(defaultValue = "asc") sortOrder: String
    ): ResponseEntity<Page<Pokemon?>> {
        return try {
            val pokemonPage = pokemonService.getPokemonByType(type, page, size, sortBy, sortOrder)
            ResponseEntity(pokemonPage, HttpStatus.OK)
        } catch (e: PokemonNotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/byAbility/{ability}")
    fun getRandomPokemonByAbility(
            @PathVariable ability: String,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "500") size: Int,
            @RequestParam(defaultValue = "id") sortBy: String,
            @RequestParam(defaultValue = "asc") sortOrder: String
    ): ResponseEntity<Page<Pokemon?>> {
        return try {
            val pokemonPage = pokemonService.getRandomPokemonByAbility(ability, page, size, sortBy, sortOrder)
            ResponseEntity(pokemonPage, HttpStatus.OK)
        } catch (e: PokemonNotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/byEggGroup/{eggGroup}")
    fun getRandomPokemonByEggGroup(
            @PathVariable eggGroup: String,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "500") size: Int,
            @RequestParam(defaultValue = "id") sortBy: String,
            @RequestParam(defaultValue = "asc") sortOrder: String
    ): ResponseEntity<Page<Pokemon?>> {
        return try {
            val pokemonPage = pokemonService.getRandomPokemonByEggGroup(eggGroup, page, size, sortBy, sortOrder)
            ResponseEntity(pokemonPage, HttpStatus.OK)
        } catch (e: PokemonNotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/random")
    fun getRandomPokemon(): ResponseEntity<Pokemon?> {
        return try {
            val pokemon = pokemonService.getRandomPokemon()
            ResponseEntity(pokemon, HttpStatus.OK)
        } catch (e: PokemonNotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}


