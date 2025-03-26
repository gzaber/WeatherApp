package com.gzaber.weatherapp.di

import com.gzaber.weatherapp.ui.search.SearchViewModel
import com.gzaber.weatherapp.ui.settings.SettingsViewModel
import com.gzaber.weatherapp.ui.weather.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { WeatherViewModel(get(), get()) }

    viewModel { SearchViewModel(get(), get()) }

    viewModel { SettingsViewModel(get()) }
}