package com.gzaber.weatherapp.data.repository.userpreferences.model

fun LocationPreferences.isNotEmpty(): Boolean {
    return this.name.isNotBlank() && this.country.isNotBlank()
}