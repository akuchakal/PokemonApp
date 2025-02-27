package com.hackerlopers.pokemonapp.data

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("base_experience")
    val baseExperience: Long? = null,
    val height: Long? = null,
    val id: Long? = null,
    val isDefault: Boolean? = null,
    val locationAreaEncounters: String? = null,
    val name: String? = null,
    val order: Long? = null,
    val sprites: Sprites? = null,
    val weight: Long? = null
)

data class Sprites (
    @SerializedName("back_default")
    val backDefault: String? = null,

    @SerializedName("back_shiny")
    val backShiny: String? = null,


    @SerializedName("front_default")
    val frontDefault: String? = null,

    @SerializedName("front_shiny")
    val frontShiny: String? = null,
)
