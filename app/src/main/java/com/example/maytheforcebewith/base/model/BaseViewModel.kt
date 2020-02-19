package com.example.maytheforcebewith.base.model

import androidx.lifecycle.ViewModel
import com.example.maytheforcebewith.injection.components.DaggerStarWarsApiViewModelInjector
import com.example.maytheforcebewith.injection.components.StarWarsApiViewModelInjector
import com.example.maytheforcebewith.injection.module.StarWarsApiModule
import com.example.maytheforcebewith.ui.details.DetailsViewModel
import com.example.maytheforcebewith.ui.main.MainViewModel

open class BaseViewModel : ViewModel() {
    private val injectorApiStarWarsApi: StarWarsApiViewModelInjector = DaggerStarWarsApiViewModelInjector
        .builder()
        .networkModule(StarWarsApiModule)
        .build()

    init {
        inject()
    }

    /**
     * Dagger inject
     */
    private fun inject() {
        when (this) {
            is MainViewModel -> injectorApiStarWarsApi.inject(this)
            is DetailsViewModel -> injectorApiStarWarsApi.inject(this)
        }
    }
}