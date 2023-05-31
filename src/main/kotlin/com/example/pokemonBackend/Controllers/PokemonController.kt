package com.example.pokemonBackend.Controllers

import com.example.pokemonBackend.model.Abillity
import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.repository.AbilityRepo
import com.example.pokemonBackend.repository.EggGroupRepo
import com.example.pokemonBackend.repository.PokemonRepository
import com.example.pokemonBackend.repository.TypeRepo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.random.Random


@RestController
@RequestMapping("/pokemon")
class PokemonController(private val pokemonRepository: PokemonRepository, private val typeRepository: TypeRepo,
                        private val abilityRepo: AbilityRepo, private val eggGroupRepo: EggGroupRepo
) {

    //make a game where you guess what pokemon it is based on description
    @GetMapping("/{id}")
    fun getPokemon(@PathVariable id: Int):ResponseEntity<Pokemon?> {
        val pokemon = pokemonRepository.findById(id).orElse(null)
        return ResponseEntity(pokemon, HttpStatus.OK)
    }

    //make a game where you guess what pokemon it is based on description
    @GetMapping("/name/{name}")
    fun getPokemonByName(@PathVariable name: String,
                         @RequestParam(defaultValue = "0") page: Int,
                         @RequestParam(defaultValue = "500") size: Int,
                         @RequestParam(defaultValue = "id") sortBy: String,
                         @RequestParam(defaultValue = "asc") sortOrder: String
    ):ResponseEntity<Page<Pokemon?>> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val pokemon = pokemonRepository.findByName(name,pageable)
        if(pokemon != null) {
            return ResponseEntity(pokemon, HttpStatus.OK)
        }
        return ResponseEntity(null, HttpStatus.NOT_FOUND)
    }
    @GetMapping("/all")
    fun getAllPokemon(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "500") size: Int,
            @RequestParam(defaultValue = "id") sortBy: String,
            @RequestParam(defaultValue = "asc") sortOrder: String
    ): ResponseEntity<Page<Pokemon>> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val pokemonPage = pokemonRepository.findAll(pageable)
        return ResponseEntity(pokemonPage, HttpStatus.OK)
    }




    //get pokemon by type
    @GetMapping("/byType/{type}")
    fun getRandomPokemonByType(
            @PathVariable type: String,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "500") size: Int,
            @RequestParam(defaultValue = "id") sortBy: String,
            @RequestParam(defaultValue = "asc") sortOrder: String
    ): ResponseEntity<Page<Pokemon?>> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val typeEntity = typeRepository.findByName(type)
        if (typeEntity != null) {
            val pokemon = pokemonRepository.findAllByTypesContainingOrderByTypes(typeEntity, pageable)
            return ResponseEntity(pokemon, HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    /**
     * get pokemon by ability
     */
    @GetMapping("/byAbility/{ability}")
    fun getRandomPokemonByAbility(
            @PathVariable ability: String,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "500") size: Int,
            @RequestParam(defaultValue = "id") sortBy: String,
            @RequestParam(defaultValue = "asc") sortOrder: String
    ): ResponseEntity<Page<Pokemon?>> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val abilityEntity = abilityRepo.findByName(ability)
        if (abilityEntity != null) {
            val pokemon = pokemonRepository.findAllByAbilitiesContainingOrderByAbilities(abilityEntity, pageable)
            return ResponseEntity(pokemon, HttpStatus.OK)
        }

        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @GetMapping("/byEggGroup/{eggGroup}")
    fun getRandomPokemonByEggGroup(
            @PathVariable eggGroup: String,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "500") size: Int,
            @RequestParam(defaultValue = "id") sortBy: String,
            @RequestParam(defaultValue = "asc") sortOrder: String
    ): ResponseEntity<Page<Pokemon?>> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val eggGroupEntity = eggGroupRepo.findByName(eggGroup)
        if (eggGroupEntity != null) {
            val pokemon = pokemonRepository.findAllByEggGroupsContainingOrderByEggGroups(eggGroupEntity, pageable)
            return ResponseEntity(pokemon, HttpStatus.OK)
        }

        return ResponseEntity(HttpStatus.NOT_FOUND)
    }




    //get random pokemon
    @GetMapping("/random")
    fun getRandomPokemon(): ResponseEntity<Pokemon?> {
        val randomInt = Random.nextInt(1,553)
        val pokemon = pokemonRepository.findById(randomInt).orElse(null)
        return ResponseEntity(pokemon, HttpStatus.OK)
    }






}
