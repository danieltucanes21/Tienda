package edu.unicauca.apimovil.pixelplaza

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
//import co.edu.unicauca.aplimovil.tienda.NavigationDrawer
import co.edu.unicauca.aplimovil.tienda.components.ProductItem
import co.edu.unicauca.aplimovil.tienda.components.NavigationDrawer
import co.edu.unicauca.aplimovil.tienda.components.PixelPlazaAppBar
import co.edu.unicauca.aplimovil.tienda.components.PixelPlazaScaffold
import co.edu.unicauca.aplimovil.tienda.components.ScreenWithAppBar
import kotlinx.coroutines.launch

@Composable
fun ShoppingScreen(productList: MutableList<ProductInfo>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Compras",
            fontSize = textTitleLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            letterSpacing = letterSpacing,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        // Lista de productos
        LazyColumn (
            modifier = Modifier.weight(1f)
        ) {
            items(productList) {
                ProductItem(it)
                Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre elementos
            }
        }
    }
}

@Preview
@Composable
fun PreviewShoppingScreen() {
    val productList = generateData()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    NavigationDrawer(navController = navController, drawerState = drawerState) {
        ScreenWithAppBar(productList = productList,
            drawerState = drawerState,
            screen = { productList, modifier ->
                ShoppingScreen(
                    productList = productList.toMutableList(),
                    modifier = modifier
                )
        })
    }
}