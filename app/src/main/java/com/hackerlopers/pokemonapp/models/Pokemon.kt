package com.hackerlopers.pokemonapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey()
    val name: String,
    @ColumnInfo(name = "fav")
    val fav: Boolean = false
)