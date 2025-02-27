package com.hackerlopers.pokemonapp

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.hackerlopers.pokemonapp.ui.theme.PokemonAppTheme
import com.hackerlopers.pokemonapp.viewModel.PokemonViewModel
import com.hackerlopers.pokemonapp.views.PokemonsView
import dagger.hilt.android.AndroidEntryPoint
import java.net.NetworkInterface
import java.util.Collections


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: PokemonViewModel by viewModels()
        setContent {
            PokemonAppTheme {
                PokemonsView(viewModel = viewModel)
            }
        }
    }

    fun getMacAddress(context: Context): String {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo

        // Verificar si la dirección MAC está disponible
        return wifiInfo.macAddress ?: "MAC no disponible"
    }
}