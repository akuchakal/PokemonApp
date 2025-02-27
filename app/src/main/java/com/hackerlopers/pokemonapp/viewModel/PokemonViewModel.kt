package com.hackerlopers.pokemonapp.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import java.net.NetworkInterface
import java.util.Collections
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repo: PokemonRepository,
    private val pokeRepo: PokemonLocalRepository
) : ViewModel() {
    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons = _pokemons.asStateFlow()

    var macDevice by mutableStateOf("")
        private set

    init {
        getPokemons()
        macDevice = getWIFIMAC()
    }

    private fun getPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.fetchPokemonsWithDetails()
            _pokemons.value = result.transform()
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
                    _pokemons.value = tmpList
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    fun getWIFIMAC(): String {
        try {
            val interfaceName = "wlan0"
            val interfaces: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())
            for (inter_face in interfaces) {
                if (!inter_face.name.equals(interfaceName, ignoreCase = true)) {
                    continue
                }

                val mac = inter_face.hardwareAddress ?: return ""

                val buffer = StringBuilder()
                for (aMac in mac) {
                    buffer.append(String.format("%02X:", aMac))
                }
                if (buffer.length > 0) {
                    buffer.deleteCharAt(buffer.length - 1)
                }
                return buffer.toString()
            }
        } catch (ignored: Exception) {
        }
        return ""
    }

    fun setFavPokemon(poke: Pokemon) {
        val pokeFav = poke.copy(
            fav = !poke.fav
        )
        viewModelScope.launch(Dispatchers.IO) {
            pokeRepo.upsertPokemon(pokeFav)
        }
    }
}