package com.hackerlopers.pokemonapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.hackerlopers.pokemonapp.components.CardPokemon
import com.hackerlopers.pokemonapp.viewModel.PokemonViewModel

@Composable
fun PokemonsView(viewModel: PokemonViewModel) {
    val pokemons = viewModel.pokemons.collectAsState()
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            LazyColumn {
                items(pokemons.value) { item ->
                    CardPokemon(pokemon = item, onClick = {
                        viewModel.setFavPokemon(item)
                    })
                }
            }
        }
    }
}