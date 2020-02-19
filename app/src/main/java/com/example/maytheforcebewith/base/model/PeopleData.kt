package com.example.maytheforcebewith.base.model

import com.example.maytheforcebewith.util.*
import com.google.gson.annotations.SerializedName

data class PeopleData(

    @SerializedName(NAME) val name: String,
    @SerializedName(HEIGHT) val height: String,
    @SerializedName(MASS) val mass: String,
    @SerializedName(HAIR_COLOR) val hairColor: String,
    @SerializedName(SKIN_COLOR) val skinColor: String,
    @SerializedName(EYE_COLOR) val eyeColor: String,
    @SerializedName(BIRTH_YEAR) val birthYear: String,
    @SerializedName(GENDER) val gender: String,
    @SerializedName(HOMEWORLD) val homeworld: String,
    @SerializedName(FILMS) val films: List<String>,
    @SerializedName(SPECIES) val species: List<String>,
    @SerializedName(VEHICLES) val vehicles: List<String>,
    @SerializedName(STARSHIPS) val starships: List<String>,
    @SerializedName(CREATED) val created: String,
    @SerializedName(EDITED) val edited: String,
    @SerializedName(URL) val url: String
)