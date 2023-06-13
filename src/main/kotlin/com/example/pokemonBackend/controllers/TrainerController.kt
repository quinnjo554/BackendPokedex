package com.example.pokemonBackend.controllers

import com.example.pokemonBackend.model.Trainer
import com.example.pokemonBackend.request.TrainerRequest
import com.example.pokemonBackend.service.TrainerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/trainer")
class TrainerController(private val trainerService: TrainerService) {

    @PostMapping("/login")
    fun signIn(@RequestBody trainerRequest: TrainerRequest): ResponseEntity<Trainer?> {
        trainerService.signIn(trainerRequest)
        return ResponseEntity(null, HttpStatus.ACCEPTED)
    }

    @PostMapping("/post")
    fun createTrainer(@RequestBody trainerRequest: TrainerRequest): ResponseEntity<Trainer> {
        trainerService.createTrainer(trainerRequest)
        return ResponseEntity(null, HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteTrainer(@PathVariable id: Long): ResponseEntity<Trainer> {
        trainerService.deleteTrainer(id)
        return ResponseEntity(null, HttpStatus.OK)
    }

    @DeleteMapping("/delete-by-email")
    fun deleteTrainerByEmail(@RequestParam email: String): ResponseEntity<Trainer> {
        val responseMessage = trainerService.deleteTrainerByEmail(email)
        return ResponseEntity(responseMessage, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getTrainerByID(@PathVariable id: Long): ResponseEntity<Trainer?> {
        val trainer = trainerService.getTrainerByID(id)
        return ResponseEntity(trainer, HttpStatus.OK)
    }

    @GetMapping("/email")
    fun getTrainerByEmail(@RequestParam email: String): ResponseEntity<Trainer?> {
        val trainer = trainerService.getTrainerByEmail(email)
        return ResponseEntity(trainer, HttpStatus.OK)
    }

    @DeleteMapping("/delete/{pokeId}/from-trainer/{trainerId}")
    fun releasePokemon(
            @PathVariable pokeId: Int,
            @PathVariable trainerId: Int,
    ) {
        trainerService.releasePokemon(pokeId, trainerId)
    }
}
