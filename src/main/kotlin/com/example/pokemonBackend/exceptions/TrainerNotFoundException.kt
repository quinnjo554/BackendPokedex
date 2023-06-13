package com.example.pokemonBackend.Exceptions

import org.springframework.http.HttpStatus

class TrainerNotFoundException(message: String) : RuntimeException(message)