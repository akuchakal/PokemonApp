package com.hackerlopers.pokemonapp.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.hackerlopers.pokemonapp.models.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDataBaseDao {
    @Query("SELECT * FROM pokemon")
    fun getPokemons(): Flow<List<Pokemon>>

    @Upsert
    fun upsertPokemons(pokemons: List<Pokemon>)

    @Update
    fun updatePokemon(pokemon: Pokemon)
}