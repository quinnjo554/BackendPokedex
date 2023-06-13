package com.example.pokemonBackend.exceptions

class AbilityNotFoundException(missingAbility: String = "Ability", message: String = "Ability Not Found: $missingAbility") : RuntimeException(message) {}