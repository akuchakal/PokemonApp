package com.hackerlopers.pokemonapp.di

import android.content.Context
import androidx.room.Room
import com.hackerlopers.pokemonapp.data.ApiPokemon
import com.hackerlopers.pokemonapp.room.PokeDatabase
import com.hackerlopers.pokemonapp.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiPokemon(retrofit: Retrofit): ApiPokemon {
        return retrofit.create(ApiPokemon::class.java)
    }

    @Singleton
    @Provides
    fun providesPokeDatabase(@ApplicationContext context: Context): PokeDatabase {
        return Room
            .databaseBuilder(
                context,
                PokeDatabase::class.java,
                "pokemon_db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

}