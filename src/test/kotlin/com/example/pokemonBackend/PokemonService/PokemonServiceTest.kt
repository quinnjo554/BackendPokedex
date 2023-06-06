package com.example.pokemonBackend.PokemonService

import com.example.pokemonBackend.Exceptions.PokemonNotFoundException
import com.example.pokemonBackend.Service.PokemonService
import com.example.pokemonBackend.model.Pokemon
import com.example.pokemonBackend.model.Type
import com.example.pokemonBackend.repository.AbilityRepo
import com.example.pokemonBackend.repository.EggGroupRepo
import com.example.pokemonBackend.repository.PokemonRepo
import com.example.pokemonBackend.repository.TypeRepo
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.Optional

@ExtendWith(MockKExtension::class)
class PokemonServiceTest {
    private lateinit var pokemonRepo: PokemonRepo
    private lateinit var typeRepo: TypeRepo
    private lateinit var abilitiesRepo: AbilityRepo
    private lateinit var eggGroupRepo: EggGroupRepo
    private lateinit var pokemonService: PokemonService

    @BeforeEach
    fun setUp() {
        pokemonRepo = mockk()
        typeRepo = mockk()
        abilitiesRepo = mockk()
        eggGroupRepo = mockk()
        pokemonService = PokemonService(pokemonRepo, typeRepo, abilitiesRepo, eggGroupRepo)
    }

    @Test
    fun testGetPokemon() {
        val pokemonId = 1
        val pokemon = Pokemon(pokemonId, "Bulbasaur")
        every { pokemonRepo.findById(pokemonId) } returns Optional.of(pokemon)
        val result = pokemonService.getPokemon(pokemonId)
        assertEquals(pokemon, result)
    }

    @Test
    fun testGetPokemonByType() {
        val type = "Fire"
        val page = 0
        val size = 100
        val sortBy = "id"
        val sortOrder = "asc"

        val pageable: PageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy)
        val typeEntity = Type(1, "Fire")
        val pokemonList = listOf(
                Pokemon(4, "Charmander"),
                Pokemon(5, "Charmeleon"),
                Pokemon(6, "Charizard")
        )
        val expectedPage = PageImpl(pokemonList, pageable, 3)
        every { typeRepo.findByName(type) } returns typeEntity
        every { pokemonRepo.findAllByTypesContainingOrderByTypes(typeEntity, pageable) } returns expectedPage
        val result = pokemonService.getPokemonByType("Fire", page, size, sortBy, sortOrder)
        assertEquals(expectedPage.content, result.content)
    }

    @Test
    fun testGetPokemon_ThrowsPokemonNotFoundException() {
        val pokemonId = -1
        every { pokemonRepo.findById(pokemonId) } returns Optional.empty()
        assertThrows(PokemonNotFoundException::class.java) {
            pokemonService.getPokemon(pokemonId)
        }
    }

    // Add more test cases for other service methods
}
