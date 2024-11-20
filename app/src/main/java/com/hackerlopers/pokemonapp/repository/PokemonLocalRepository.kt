package com.hackerlopers.pokemonapp.repository

import com.hackerlopers.pokemonapp.models.Pokemon
import com.hackerlopers.pokemonapp.room.PokeDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonLocalRepository @Inject constructor(private val pokeDataBaseDao: PokeDataBaseDao) {


    fun getPokemons(): Flow<List<Pokemon>> {
        return pokeDataBaseDao.getPokemons().flowOn(Dispatchers.IO).conflate()
    }

    suspend fun upsertPokemon(pokemon: Pokemon) = pokeDataBaseDao.upsertPokemon(pokemon)
}