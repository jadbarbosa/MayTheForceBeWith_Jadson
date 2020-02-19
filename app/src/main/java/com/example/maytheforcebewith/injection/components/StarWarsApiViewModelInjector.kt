package com.example.maytheforcebewith.injection.components

import com.example.maytheforcebewith.injection.module.StarWarsApiModule
import com.example.maytheforcebewith.ui.details.DetailsViewModel
import com.example.maytheforcebewith.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for ViewModels.
 */
@Singleton
@Component(modules = [StarWarsApiModule::class])
interface StarWarsApiViewModelInjector {

    /**
     * Injects required dependencies into the specified MainViewModel.
     * @param mainViewModel MainViewModel in which to inject the dependencies
     */
    fun inject(mainViewModel: MainViewModel)

    /**
     * Injects required dependencies into the specified DetailsViewModel.
     * @param detailsViewModel DetailsViewModel in which to inject the dependencies
     */
    fun inject(detailsViewModel: DetailsViewModel)

    @Component.Builder
    interface Builder {

        fun build(): StarWarsApiViewModelInjector
        fun networkModule(networkModule: StarWarsApiModule): Builder
    }
}