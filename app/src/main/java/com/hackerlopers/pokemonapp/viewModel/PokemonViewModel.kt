package com.hackerlopers.pokemonapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackerlopers.pokemonapp.data.RemotePokemon
import com.hackerlopers.pokemonapp.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repo: PokemonRepository): ViewModel() {
    private val _pokemons = MutableStateFlow<List<RemotePokemon>>(emptyList())
    val pokemons = _pokemons.asStateFlow()

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getPokemons()
            _pokemons.value = result
        }
    }
}