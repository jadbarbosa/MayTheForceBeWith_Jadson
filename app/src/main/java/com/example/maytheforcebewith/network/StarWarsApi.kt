package com.example.maytheforcebewith.network

import com.example.maytheforcebewith.BuildConfig.WEBHOOK_URL
import com.example.maytheforcebewith.base.model.*
import com.example.maytheforcebewith.util.URL_PERSON
import com.example.maytheforcebewith.util.URL_PLANETS
import com.example.maytheforcebewith.util.URL_SPECIES
import com.example.maytheforcebewith.util.URL_STARSHIPS
import retrofit2.Response
import retrofit2.http.*

interface StarWarsApi {

    @GET(URL_PERSON)
    suspend fun getPeople(): Response<ApiData>

    @GET
    suspend fun getPeople(@Url nextUrl : String): Response<ApiData>

    @GET
    suspend fun getPerson(@Url personUrl : String): Response<PeopleData>

    @POST
    suspend fun postFavorite(@Body favoritePerson : PeopleData, @Url webhookUrl: String = WEBHOOK_URL): Response<Void>

    @GET(URL_SPECIES)
    suspend fun getSpecie(@Path("specieId") specieId : String): Response<SpeciesData>

    @GET(URL_PLANETS)
    suspend fun getPlanet(@Path("planetId") planetId: String): Response<PlanetsData>

    @GET(URL_STARSHIPS)
    suspend fun getStarships(@Path("starshipId") starshipId: String): Response<StarshipsData>
}