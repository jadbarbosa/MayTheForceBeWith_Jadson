package com.example.maytheforcebewith.ui.details

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.maytheforcebewith.base.model.*
import com.example.maytheforcebewith.network.StarWarsApi
import com.example.maytheforcebewith.ui.details.repository.DetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsViewModel : BaseViewModel() {
    @Inject
    lateinit var starWarsApi: StarWarsApi

    private val repository by lazy { DetailsRepository(starWarsApi) }

    val personData = MutableLiveData<PeopleData>().apply { value = null }

    val speciesData = MutableLiveData<String>().apply { value = null }

    val planetsData = MutableLiveData<PlanetsData>().apply { value = null }

    val starshipsData = MutableLiveData<String>().apply { value = null }

    val successRequest = MutableLiveData<Int>().apply { value = 0 }

    fun getPerson(personUrl: String) {
        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {
                repository.getPerson(personUrl)
            }

            if (result is PeopleData) {
                personData.value = result
            }

            var speciesList: MutableList<String> = mutableListOf()

            if (personData.value!!.species != null) {
                var speciesUriList = personData.value!!.species

                for(speciesUri: String in speciesUriList) {
                    var specieId = Uri.parse(speciesUri).lastPathSegment
                    speciesList.add(repository.getSpecies(specieId!!).name)
                }
            }



            if (speciesList.isNotEmpty()) {
                speciesData.value = speciesList.joinToString()
            } else {
                speciesData.value = "N/A"
            }

            val planetId = Uri.parse(personData.value!!.homeworld).lastPathSegment

            val resultPlanets = withContext(Dispatchers.IO) {
                if (planetId != null) {
                    repository.getPlanets(planetId)
                } else {
                    PlanetsData("N/A")
                }
            }

            if (resultPlanets is PlanetsData) {
                planetsData.value = resultPlanets
            }

            var starshipsList: MutableList<String> = mutableListOf()

            if (personData.value!!.starships != null) {
                var starshipsUriList = personData.value!!.starships

                for(starshipUri: String in starshipsUriList){
                    var starshipsId = Uri.parse(starshipUri).lastPathSegment
                    starshipsList.add(repository.getStarships(starshipsId!!).name)
                }
            }

            if(starshipsList.isNotEmpty()){
                starshipsData.value = starshipsList.joinToString()
            } else{
                starshipsData.value = "N/A"
            }

        }
    }

    fun postFavorite() {
        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {
                repository.postFavorite(personData.value!!)
            }
            if (result is Int && result == 200) {
                successRequest.value = 1
            }

        }
    }
}
