package com.example.maytheforcebewith.base.model

import com.example.maytheforcebewith.util.NAME
import com.google.gson.annotations.SerializedName

data class StarshipsData(

    @SerializedName(NAME) val name: String
)