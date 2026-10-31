package com.example.lazycolumn_ulkelistesi

// Ülke Veri Modeli
data class Country(
    val name: String,
    val capital: String,
    val currency: String,
    val flagResId: Int
)


val countries = listOf(
    
    Country("Almanya", "Berlin", "Euro", R.drawable.germany),
    Country("Türkiye", "Ankara", "TRY", R.drawable.turkey)
).groupBy { it.name.first() }.toSortedMap()
