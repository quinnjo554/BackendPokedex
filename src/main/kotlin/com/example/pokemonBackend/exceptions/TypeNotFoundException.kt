package com.example.pokemonBackend.exceptions

class TypeNotFound(missingType: String = "Type", message: String = "Type Not Found: $missingType") : RuntimeException(message) {}
