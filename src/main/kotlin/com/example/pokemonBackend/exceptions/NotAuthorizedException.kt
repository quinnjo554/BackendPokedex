package com.example.pokemonBackend.exceptions

class NotAuthorizedException(message: String = "Not Authorized") : RuntimeException(message) {}