package com.example.pokemonBackend.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

class ControllerAdvice {
    @ControllerAdvice
    class ExceptionHandlerController {
        @ExceptionHandler(PokemonNotFoundException::class)
        fun handlePokemonNotFoundException(exception: PokemonNotFoundException): ResponseEntity<String> {
            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND) //is this bad that its returning a string message?
        }

        @ExceptionHandler(PokeTrainerNotFoundException::class)
        fun handlePokeTrainerNotFoundException(exception: PokeTrainerNotFoundException): ResponseEntity<String> {
            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
        }

        @ExceptionHandler(TrainerNotFoundException::class)
        fun handleTrainerNotFoundException(exception: TrainerNotFoundException): ResponseEntity<String> {
            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
        }

        @ExceptionHandler(OperationFailedException::class)
        fun handleOperationFailedException(exception: OperationFailedException): ResponseEntity<String> {
            return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
        }

        @ExceptionHandler(AlreadyExistsException::class)
        fun handleOperationFailedException(exception: AlreadyExistsException): ResponseEntity<String> {
            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
        }

        @ExceptionHandler(NotAuthorizedException::class)
        fun handleOperationFailedException(exception: NotAuthorizedException): ResponseEntity<String> {
            return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
        }

        @ExceptionHandler(TypeNotFoundException::class)
        fun handleOperationFailedException(exception: TypeNotFoundException): ResponseEntity<String> {
            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
        }

        @ExceptionHandler(AbilityNotFoundException::class)
        fun handleOperationFailedException(exception: AbilityNotFoundException): ResponseEntity<String> {
            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
        }

        @ExceptionHandler(Exception::class)
        fun handleException(exception: Exception): ResponseEntity<String> {
            return ResponseEntity("An error occurred: ${exception.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}