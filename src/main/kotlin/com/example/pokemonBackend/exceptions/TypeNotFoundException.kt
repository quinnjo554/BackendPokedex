package com.example.pokemonBackend.exceptions

class TypeNotFoundException(missingType: String = "Type", message: String = "Type Not Found: $missingType") : RuntimeException(message) {}
