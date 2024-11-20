package com.hackerlopers.pokemonapp.data

import com.hackerlopers.pokemonapp.models.Pokemon

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

fun RemotePokemon.transform(): Pokemon {
    this.apply {
        return Pokemon(
            name = name.orEmpty(),
            fav = false
        )
    }
}

fun List<RemotePokemon>.transform(): List<Pokemon> {
    return this.map {
        it.transform()
    }
}