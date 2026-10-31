package com.example.lazycolumn_ulkelistesi

// Ülke Veri Modeli
data class Country(
    val name: String,
    val capital: String,
    val currency: String,
    val flagResId: Int
)

// Hocanın listeyi oluşturup doğrudan grupladığı yer
val countries = listOf(
    // Kendi ülkelerini buraya eklemeye devam edebilirsin
    Country("Almanya", "Berlin", "Euro", R.drawable.germany),
    Country("Türkiye", "Ankara", "TRY", R.drawable.turkey)
).groupBy { it.name.first() }.toSortedMap()