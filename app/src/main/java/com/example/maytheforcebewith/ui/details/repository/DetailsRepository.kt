package com.example.maytheforcebewith.ui.details.repository

import com.example.maytheforcebewith.base.model.PeopleData
import com.example.maytheforcebewith.base.model.PlanetsData
import com.example.maytheforcebewith.base.model.SpeciesData
import com.example.maytheforcebewith.base.model.StarshipsData
import com.example.maytheforcebewith.network.StarWarsApi

open class DetailsRepository(private val starWarsApi: StarWarsApi) {
    suspend fun postFavorite(favoritePerson: PeopleData): Int {
        val request = starWarsApi.postFavorite(favoritePerson)
        return request.code()
    }

    suspend fun getPerson(personUrl: String): PeopleData {
        val result = starWarsApi.getPerson(personUrl)
        return result.body()!!
    }

    suspend fun getSpecies(specieId: String): SpeciesData {
        val specieResult = starWarsApi.getSpecie(specieId)
        return specieResult.body()!!
    }

    suspend fun getPlanets(planetId: String): PlanetsData {
        val planetsResult = starWarsApi.getPlanet(planetId)
        return planetsResult.body()!!
    }

    suspend fun getStarships(starshipId: String): StarshipsData {
        val starshipsResult = starWarsApi.getStarships(starshipId)
        return starshipsResult.body()!!
    }
}