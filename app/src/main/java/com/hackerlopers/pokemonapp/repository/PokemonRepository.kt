package com.hackerlopers.pokemonapp.repository

import com.hackerlopers.pokemonapp.data.ApiPokemon
import com.hackerlopers.pokemonapp.data.PokemonResponse
import com.hackerlopers.pokemonapp.data.RemotePokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
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

    suspend fun fetchPokemonsWithDetails(): List<RemotePokemon> = coroutineScope {
        val listResponse = apiPokemon.getPokemons()
        // Supongamos que listResponse.results es la lista con { name, url }
        val listDetails = listResponse.body()?.results?.map { pokemon ->
            // Llamada concurrente a cada detalle
            async {
                pokemon.copy(
                    detail = pokemon.url?.let { apiPokemon.getPokemonDetails(it).body() }
                )
            }
        }?.awaitAll()
        listDetails ?: emptyList()
    }

}