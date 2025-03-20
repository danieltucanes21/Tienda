package edu.unicauca.apimovil.pixelplaza

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import co.edu.unicauca.aplimovil.tienda.ProductItem
import co.edu.unicauca.aplimovil.tienda.R

@Composable
fun ShoppingScreen(productList: MutableList<ProductInfo>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Compras",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 14.sp,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally),
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewShoppingScreen() {

    val productList = mutableListOf(
        ProductInfo(
            painter = painterResource(R.drawable.image_list_1), // Placeholder de imagen
            contentDescription = "Camiseta Negra",
            description = "Camiseta de algodón negra con diseño minimalista.",
            price = 199.99,
            color = "Negro",
            brand = "Nike",
            sizes = listOf(Size.S, Size.M, Size.L, Size.XL),
            specifications = "100% algodón, transpirable",
            score = 5,
            publicType = PublicType.MEN
        ),
        ProductInfo(
            painter = painterResource(R.drawable.image_list_2),
            contentDescription = "Vestido Rojo",
            description = "Vestido elegante rojo con corte ajustado.",
            price = 349.99,
            color = "Rojo",
            brand = "Zara",
            sizes = listOf(Size.S, Size.M, Size.L),
            specifications = "Poliéster y elastano, ideal para eventos",
            score = 4,
            publicType = PublicType.WOMEN
        ),
        ProductInfo(
            painter = painterResource(R.drawable.image_list_3),
            contentDescription = "Pantalón Deportivo",
            description = "Pantalón jogger cómodo para entrenamiento.",
            price = 259.99,
            color = "Gris",
            brand = "Adidas",
            sizes = listOf(Size.M, Size.L, Size.XL),
            specifications = "Tela ligera, ajuste perfecto",
            score = 5,
            publicType = PublicType.MEN
        ),
        ProductInfo(
            painter = painterResource(R.drawable.image_list_4),
            contentDescription = "Blusa Blanca",
            description = "Blusa formal de manga larga y corte clásico.",
            price = 299.99,
            color = "Blanco",
            brand = "H&M",
            sizes = listOf(Size.S, Size.M, Size.L, Size.XL),
            specifications = "Tela fresca, ideal para oficina",
            score = 4,
            publicType = PublicType.WOMEN
        ),
        ProductInfo(
            painter = painterResource(R.drawable.image_list_5),
            contentDescription = "Zapatillas Running",
            description = "Zapatillas deportivas con amortiguación superior.",
            price = 499.99,
            color = "Azul",
            brand = "Nike",
            sizes = listOf(Size.S, Size.M, Size.L, Size.XL, Size.XXL),
            specifications = "Tecnología Air, suela antideslizante",
            score = 5,
            publicType = PublicType.MEN
        ),
        ProductInfo(
            painter = painterResource(R.drawable.image_list_6),
            contentDescription = "Sudadera con Capucha",
            description = "Sudadera térmica con interior afelpado.",
            price = 329.99,
            color = "Negro",
            brand = "Puma",
            sizes = listOf(Size.M, Size.L, Size.XL),
            specifications = "Material térmico, bolsillo frontal",
            score = 4,
            publicType = PublicType.WOMEN
        ),
        ProductInfo(
            painter = painterResource(R.drawable.image_list_7),
            contentDescription = "Abrigo Largo",
            description = "Abrigo largo de invierno con botones metálicos.",
            price = 699.99,
            color = "Beige",
            brand = "Mango",
            sizes = listOf(Size.S, Size.M, Size.L),
            specifications = "Lana y poliéster, ideal para frío intenso",
            score = 5,
            publicType = PublicType.WOMEN
        ),
        ProductInfo(
            painter = painterResource(R.drawable.image_list_8),
            contentDescription = "Chaqueta Deportiva Niño",
            description = "Chaqueta impermeable para niños, con capucha.",
            price = 279.99,
            color = "Verde",
            brand = "The North Face",
            sizes = listOf(Size.S, Size.M, Size.L),
            specifications = "Resistente al agua, transpirable",
            score = 5,
            publicType = PublicType.CHILD
        )
    )
//    ShoppingScreen(productList)


    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pixel Plaza") },
                navigationIcon = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(onClick = { /* Acción para la opción 1 */ }) {
                            Text("Opción 1")
                        }
                        DropdownMenuItem(onClick = { /* Acción para la opción 2 */ }) {
                            Text("Opción 2")
                        }
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .padding(end = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo), // Reemplaza con tu recurso de logo
                            contentDescription = "Logo",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ShoppingScreen(
            productList = productList,
            modifier = Modifier.padding(innerPadding))
    }
}

fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {

}