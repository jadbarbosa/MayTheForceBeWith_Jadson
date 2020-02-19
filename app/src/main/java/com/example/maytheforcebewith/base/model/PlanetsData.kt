package com.example.maytheforcebewith.base.model

import com.example.maytheforcebewith.util.NAME
import com.google.gson.annotations.SerializedName

data class PlanetsData(
        @SerializedName(NAME) val name: String
)