package com.example.pokemonBackend.exceptions

class PokemonNotFoundException(message: String = "Pokemon Not Found") : RuntimeException(message)