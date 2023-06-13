package com.example.pokemonBackend.exceptions

class AbilityNotFound(missingType: String = "Ability", message: String = "Ability Not Found: $missingType") : RuntimeException(message) {}