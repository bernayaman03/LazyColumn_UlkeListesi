package com.example.lazycolumn_ulkelistesi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Hocanın MainActivity'de tanımladığı Kategori Sınıfı
data class CountryCategory(
    val firstLetterOfName: Char,
    val countryList: List<Country>
)

// 2. Hocanın diğer dosyadan çektiği veriyi map ile çevirme işlemi
private val categorizedCountries = countries.map {
    CountryCategory(
        firstLetterOfName = it.key,
        countryList = it.value
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                CategorizedLazyColumn(
                    categories = categorizedCountries,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun CategoryHeader(
    firstCharacter: Char,
    modifier: Modifier = Modifier
) {
    Text(
        text = firstCharacter.toString(),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}

@Composable
private fun CountryRowItem(
    country: Country,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = country.flagResId),
            contentDescription = null, // Hocanın icon'unda yaptığı gibi description null
            modifier = Modifier.size(width = 80.dp, height = 55.dp),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = country.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = country.capital, fontSize = 13.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = country.currency, fontSize = 13.sp, color = Color.Gray)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(
    categories: List<CountryCategory>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        categories.forEach { category ->
            stickyHeader {
                CategoryHeader(category.firstLetterOfName)
            }
            items(category.countryList) { country ->
                CountryRowItem(country)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CategorizedLazyColumn(categorizedCountries)
}