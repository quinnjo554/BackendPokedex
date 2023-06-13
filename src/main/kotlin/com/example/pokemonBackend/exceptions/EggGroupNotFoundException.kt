package com.example.pokemonBackend.exceptions

class EggGroupNotFoundException(missingGroup: String = "Egg Group", message: String = "Egg Group Not Found: $missingGroup") : RuntimeException(message) {}