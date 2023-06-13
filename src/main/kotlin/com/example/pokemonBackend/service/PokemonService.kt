package com.example.pokemonBackend.service

import com.example.pokemonBackend.exceptions.AbilityNotFoundException
import com.example.pokemonBackend.exceptions.PokemonNotFoundException
import com.example.pokemonBackend.exceptions.TypeNotFoundException
import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.repository.AbilityRepo
import com.example.pokemonBackend.repository.EggGroupRepo
import com.example.pokemonBackend.repository.PokemonRepo
import com.example.pokemonBackend.repository.TypeRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class PokemonService(
        private val pokemonRepository: PokemonRepo,
        private val typeRepository: TypeRepo,
        private val abilityRepo: AbilityRepo,
        private val eggGroupRepo: EggGroupRepo,
) {

    fun getPokemon(id: Int): Pokemon {
        return pokemonRepository.findById(id).orElseThrow {
            PokemonNotFoundException()
        }
    }

    fun getPokemonByName(name: String): Pokemon {
        return pokemonRepository.findByName(name) ?: throw PokemonNotFoundException()
    }

    fun getAllPokemon(
            @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable,
    ): Page<Pokemon> {
        return pokemonRepository.findAll(pageable)
    }

    fun getPokemonByType(
            type: String,
            @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable,
    ): Page<Pokemon> {
        val typeEntity = typeRepository.findByName(type) ?: throw TypeNotFoundException(type)
        return pokemonRepository.findAllByTypesContainingOrderByTypes(typeEntity, pageable)
    }

    fun getPokemonByAbility(
            ability: String,
            @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable,
    ): Page<Pokemon> {
        val abilityEntity = abilityRepo.findByName(ability)
                ?: throw AbilityNotFoundException(ability)
        return pokemonRepository.findAllByAbilitiesContainingOrderByAbilities(abilityEntity, pageable)
    }

    fun getPokemonByEggGroup(
            eggGroup: String,
            @PageableDefault(size = 10, sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable,
    ): Page<Pokemon> {
        val eggGroupEntity = eggGroupRepo.findByName(eggGroup)
                ?: throw PokemonNotFoundException("Pokemon with egg group $eggGroup not found")
        return pokemonRepository.findAllByEggGroupsContainingOrderByEggGroups(eggGroupEntity, pageable)
    }

    fun getRandomPokemon(): Pokemon {
        val maxPokemon = pokemonRepository.findAll().count()
        val randomInt = Random.nextInt(1, maxPokemon)
        return pokemonRepository.findById(randomInt).orElseThrow() {
            throw PokemonNotFoundException("Could not get Pokemon")
        }
    }
}
