package com.hackerlopers.pokemonapp.data

import com.hackerlopers.pokemonapp.utils.Constants.Companion.ENDPOINT_POKEMONS
import retrofit2.Response
import retrofit2.http.GET

interface ApiPokemon {

    @GET(ENDPOINT_POKEMONS)
    suspend fun getPokemons(): Response<PokemonsResponse>
}