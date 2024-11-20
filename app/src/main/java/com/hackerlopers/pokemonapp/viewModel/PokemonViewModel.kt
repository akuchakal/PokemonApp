package com.hackerlopers.pokemonapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackerlopers.pokemonapp.data.RemotePokemon
import com.hackerlopers.pokemonapp.data.transform
import com.hackerlopers.pokemonapp.models.Pokemon
import com.hackerlopers.pokemonapp.repository.PokemonLocalRepository
import com.hackerlopers.pokemonapp.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repo: PokemonRepository,
    private val pokeRepo: PokemonLocalRepository
) : ViewModel() {
    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons = _pokemons.asStateFlow()

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getPokemons()
            _pokemons.value = result.transform()
            Log.e("API", "Respuesta de la request!")
            if (pokemons.value.isNotEmpty()) {
                fetchLocalPokemons()
            }
        }
    }

    private fun fetchLocalPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            pokeRepo.getPokemons().collect() { pokes ->
                if(!pokes.isNullOrEmpty()) {
                    var tmpList = mutableListOf<Pokemon>()
                    pokemons.value.forEach { item ->
                        val pokeFound = pokes.find { lp -> lp.name == item.name }
                        if (pokeFound != null) {
                            tmpList.add(item.copy(fav = pokeFound.fav))
                        } else {
                            tmpList.add(item)
                        }
                    }
                }
                Log.e("ROOM", "Respuesta de room!")
            }
        }
    }
}