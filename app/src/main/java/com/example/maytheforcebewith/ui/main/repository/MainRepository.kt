package com.example.maytheforcebewith.ui.main.repository

import com.example.maytheforcebewith.base.model.ApiData
import com.example.maytheforcebewith.network.StarWarsApi

open class MainRepository(private val starWarsApi: StarWarsApi) {
    suspend fun getPeopleNextPage(): ApiData {
        return starWarsApi.getPeople().body()!!
    }
    suspend fun getPeopleNextPage(nextUrl: String): ApiData {
        return starWarsApi.getPeople(nextUrl).body()!!
    }
}