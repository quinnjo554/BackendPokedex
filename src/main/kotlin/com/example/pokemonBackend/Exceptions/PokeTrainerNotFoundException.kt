package com.example.pokemonBackend.Exceptions

import org.springframework.http.HttpStatus

class PokeTrainerNotFoundException(message: String) : RuntimeException(message) {
    val status: HttpStatus = HttpStatus.NOT_FOUND
}