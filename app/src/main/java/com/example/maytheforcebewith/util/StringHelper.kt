package com.example.maytheforcebewith.util

import java.text.SimpleDateFormat
import java.util.*

fun String.parseDate(): Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    return dateFormat.parse(this)!!
}
