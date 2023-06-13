package com.example.pokemonBackend.Service

import com.example.pokemonBackend.Exceptions.PokemonNotFoundException
import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.repository.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class PokemonService(private val pokemonRepository: PokemonRepo,
                     private val typeRepository: TypeRepo,
                     private val abilityRepo: AbilityRepo,
                     private val eggGroupRepo: EggGroupRepo
) {
    companion object {
        const val MAX_POKEMON = 553
    }
    fun getPokemon(id: Int): Pokemon? {
        return pokemonRepository.findById(id).orElseThrow(){
            PokemonNotFoundException("Pokemon not found")
        }
    }

    fun getPokemonByName(name: String, ): Pokemon? {
        return pokemonRepository.findByName(name) ?: throw PokemonNotFoundException("Pokemon with name: $name not found")
    }

    fun getAllPokemon(page: Int,
                      size: Int,
                      sortBy: String,
                      sortOrder: String): Page<Pokemon> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        return pokemonRepository.findAll(pageable)
    }

    fun getPokemonByType(
            type: String,
            page: Int,
            size: Int,
            sortBy: String,
            sortOrder: String
    ): Page<Pokemon?> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val typeEntity = typeRepository.findByName(type) ?: throw PokemonNotFoundException("Type '$type' not found") // Throw exception if type is not found
        return pokemonRepository.findAllByTypesContainingOrderByTypes(typeEntity, pageable)
    }

    fun getRandomPokemonByAbility(
            ability: String,
            page: Int,
            size: Int,
            sortBy: String,
            sortOrder: String
    ): Page<Pokemon?> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val abilityEntity = abilityRepo.findByName(ability) ?: throw PokemonNotFoundException("Pokemon with $ability not found")
        return pokemonRepository.findAllByAbilitiesContainingOrderByAbilities(abilityEntity, pageable)
    }

    fun getRandomPokemonByEggGroup(
            eggGroup: String,
            page: Int,
            size: Int,
            sortBy: String,
            sortOrder: String
    ): Page<Pokemon?> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val eggGroupEntity = eggGroupRepo.findByName(eggGroup) ?: throw PokemonNotFoundException("Pokemon with egg group $eggGroup not found")
        return pokemonRepository.findAllByEggGroupsContainingOrderByEggGroups(eggGroupEntity, pageable)
    }

    fun getRandomPokemon(): Pokemon? {
        val randomInt = Random.nextInt(1, MAX_POKEMON)
        return pokemonRepository.findById(randomInt).orElseThrow(){
            throw PokemonNotFoundException("Could not get Pokemon")
        }
    }
}

