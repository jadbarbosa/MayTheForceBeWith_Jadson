package com.example.maytheforcebewith.util.helpers

import android.annotation.SuppressLint
import android.content.Context
import com.example.maytheforcebewith.MayTheForceBeWithApplication


private const val PREFS_PRIVATE = "PREFS_PRIVATE"


fun setPreferencesSet(arrayList: List<String>, key : String, clear: Boolean, context: Context) {
    if (clear) {
        val settings = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
        settings.edit().remove(key).apply()
    }
    val sharedPreferences = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    val prefsPrivateEditor = sharedPreferences.edit()
    val set = HashSet<String>()
    set.addAll(arrayList)
    prefsPrivateEditor.putStringSet(key, set)
    prefsPrivateEditor.apply()
}

fun getPreferencesSet(key : String, context: Context) : MutableSet<String>? {
    var sharedPreferences = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE)
    val retorno = sharedPreferences.getStringSet(key, null)
    return retorno
}