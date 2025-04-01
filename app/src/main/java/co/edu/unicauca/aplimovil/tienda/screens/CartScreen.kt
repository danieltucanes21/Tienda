package edu.unicauca.apimovil.pixelplaza

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.components.ProductItem
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.components.NavigationDrawer
import co.edu.unicauca.aplimovil.tienda.components.ScreenWithAppBar
import co.edu.unicauca.aplimovil.tienda.navigation.Navigator
import co.edu.unicauca.aplimovil.tienda.navigation.Screen
import co.edu.unicauca.aplimovil.tienda.viewModel.NavigationViewModel

@Composable
fun CartScreen(productList: MutableList<ProductInfo>, modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp) // Padding adicional para el contenido
    ) {
        // Título
        Text(
            text = "Carrito",
            fontSize = textTitleLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            letterSpacing = letterSpacing,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        // Lista de productos con un weight para ocupar el espacio restante
        LazyColumn(
            modifier = Modifier.weight(1f) // Ocupa el espacio restante
        ) {
            items(productList) {
                ProductItem(it)
                Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre elementos
            }
        }

        // Sección del total de la compra
        CartTotalSection(total = productList.sumOf { it.price })
    }
}

@Preview
@Composable
fun CartScreenPreview () {
    val productList = generateData()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    NavigationDrawer(navController = navController, drawerState = drawerState) {
        ScreenWithAppBar(productList = productList,
            drawerState = drawerState,
            screen = { productList, modifier ->
                CartScreen(
                    productList = productList.toMutableList(),
                    modifier = modifier
                )
            })
    }
}

@Composable
fun CartTotalSection(total: Double) {
    val viewModel: NavigationViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(MaterialTheme.colorScheme.surface)
            .wrapContentSize(
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Monto total:",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = textLabelLarge,
                fontFamily = MaterialTheme.typography.labelLarge.fontFamily)
            Text(
                text = "$$total",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = textLabelLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = MaterialTheme.typography.labelLarge.fontFamily)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { Navigator.navigateTo(Screen.Card.route) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Realizar compra", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}