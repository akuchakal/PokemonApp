package com.hackerlopers.pokemonapp.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hackerlopers.pokemonapp.viewModel.PokemonViewModel

@Composable
fun PokemonsView(viewModel: PokemonViewModel) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
//            Text(text = )
        }
    }
}