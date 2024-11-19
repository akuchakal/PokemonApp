package com.hackerlopers.pokemonapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hackerlopers.pokemonapp.models.Pokemon

@Database(
    entities = [Pokemon::class],
    version = 1,
    exportSchema = false
)
abstract class PokeDatabase: RoomDatabase() {
    abstract fun pokeDao(): PokeDataBaseDao
}