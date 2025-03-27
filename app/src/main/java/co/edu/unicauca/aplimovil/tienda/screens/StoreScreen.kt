package edu.unicauca.apimovil.pixelplaza

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.components.NavigationDrawer
import co.edu.unicauca.aplimovil.tienda.components.ProductItemGridCard
import co.edu.unicauca.aplimovil.tienda.components.ScreenWithAppBar
import co.edu.unicauca.aplimovil.tienda.components.SearchBar

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreenPreview () {
    val productList = generateData()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    NavigationDrawer(navController = navController, drawerState = drawerState) {
        ScreenWithAppBar(productList = productList,
            drawerState = drawerState,
            screen = { productList, modifier ->
                StoreScreen(
                    productList = productList.toMutableList(),
                    modifier = modifier
                )
            })
    }
}

@Composable
fun StoreScreen(productList: MutableList<ProductInfo>, modifier: Modifier = Modifier) {
    var selectedCategory by remember { mutableIntStateOf(0) }
    val categoriesList = listOf("Mujeres", "Hombres", "Niños")


    Column(modifier = modifier.fillMaxSize()) {
        // Barra de búsqueda
        SearchBar()

        // Pestañas de categorías
        TabRow (selectedTabIndex = selectedCategory, containerColor = MaterialTheme.colorScheme.surface) {
            categoriesList.forEachIndexed { index, title ->
                Tab(
                    selected = selectedCategory == index,
                    onClick = { selectedCategory = index },
                    text = { Text(title, color = MaterialTheme.colorScheme.onSurface) }
                )
            }
        }

        // Lista de productos en Grid (2 columnas)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp)
        ) {
            items(productList) { product ->
                ProductItemGridCard(
                    product = product,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(0.75f)
                )
            }
        }
    }
}