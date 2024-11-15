package com.hackerlopers.pokemonapp.data

data class PokemonsResponse (
    val count: Long? = null,
    val next: String? = null,
    val previous: Any? = null,
    val results: List<RemotePokemon>? = null
)

data class RemotePokemon (
    val name: String? = null,
    val url: String? = null
)