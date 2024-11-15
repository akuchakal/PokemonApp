package com.hackerlopers.pokemonapp.repository

import com.hackerlopers.pokemonapp.data.ApiPokemon
import com.hackerlopers.pokemonapp.data.RemotePokemon
import javax.inject.Inject


class PokemonRepository @Inject constructor(private val apiPokemon: ApiPokemon) {

    suspend fun getPokemons(): List<RemotePokemon> {
        val res = apiPokemon.getPokemons()
        return if (res.isSuccessful) {
            res.body()?.results ?: emptyList()
        }
        else {
            emptyList()
        }
    }

}