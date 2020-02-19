package com.example.maytheforcebewith.base.model

import com.google.gson.annotations.SerializedName

data class ApiData(
    val count: Int,
    @SerializedName("next") val nextUrl: String,
    @SerializedName("previous") val previousUrl: String,
    val results: List<PeopleData>
)