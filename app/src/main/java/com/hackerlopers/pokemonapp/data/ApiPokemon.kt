package com.hackerlopers.pokemonapp.data

import com.hackerlopers.pokemonapp.utils.Constants.Companion.ENDPOINT_POKEMONS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiPokemon {

    @GET(ENDPOINT_POKEMONS)
    suspend fun getPokemons(): Response<PokemonsResponse>

    @GET
    suspend fun getPokemonDetails(@Url url: String): Response<PokemonResponse>
}