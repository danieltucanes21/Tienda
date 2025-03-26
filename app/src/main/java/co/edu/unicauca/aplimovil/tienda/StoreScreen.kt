package edu.unicauca.apimovil.pixelplaza

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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
import co.edu.unicauca.aplimovil.tienda.ProductItem
import co.edu.unicauca.aplimovil.tienda.R

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreenPreview () {
    val categoriesList = listOf("Mujeres", "Hombres", "Niños")
    val productList = generateData()


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
//                        DropdownMenuItem(onClick = { /* Acción para la opción 1 */ }) {
//                            Text("Opción 1")
//                        }
//                        DropdownMenuItem(onClick = { /* Acción para la opción 2 */ }) {
//                            Text("Opción 2")
//                        }
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
        // Aplicar el innerPadding al contenido principal
        StoreScreen(
            categoriesList = categoriesList,
            productList = productList,
            modifier = Modifier.padding(innerPadding) // Aquí aplicamos el padding
        )
    }
}

@Composable
fun StoreScreen(categoriesList: List<String>, productList: MutableList<ProductInfo>, modifier: Modifier = Modifier) {
    var selectedCategory by remember { mutableIntStateOf(0) }



    Column(modifier = modifier.fillMaxSize().background(Color(0xFF121212))) {
        // Barra de búsqueda
        SearchBar()

        // Pestañas de categorías
        TabRow (selectedTabIndex = selectedCategory, containerColor = Color.Black) {
            categoriesList.forEachIndexed { index, title ->
                Tab(
                    selected = selectedCategory == index,
                    onClick = { selectedCategory = index },
                    text = { Text(title, color = if (selectedCategory == index) Color.White else Color.Gray) }
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
                        .padding(4.dp) // Espaciado entre celdas
                        .fillMaxWidth() // Ocupa todo el ancho disponible
                        .aspectRatio(0.75f) // Relación de aspecto (ancho/alto)
                )
            }
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = { /* TODO: Implementar búsqueda */ },
        placeholder = { Text("Buscar en la tienda", color = Color.Gray) },
        leadingIcon = { Icon(
            Icons.Default.Search,
            contentDescription = "Buscar",
            tint = Color.White) },
        trailingIcon = { Icon(
            Icons.Default.AccountCircle,
            contentDescription = "Perfil",
            tint = Color(0xFF9C27B0)) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(50.dp)
    )
}

@Composable
fun ProductItemGridCard(product: ProductInfo, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            colors = CardDefaults.cardColors(Color.Gray),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box {
                    Image(
                        painter = product.painter,
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .height(160.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = "$${product.price}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = product.description,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // Botón "+" solapado (mitad en imagen, mitad en precio)
        IconButton(
            onClick = { /* TODO: Agregar al carrito */ },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-48).dp)
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Agregar",
                tint = Color(0xFF9C27B0),
                modifier = Modifier.size(48.dp)
            )
        }
    }
}


@Preview
@Composable
fun ProductItemGridCardPreview () {
    ProductItemGridCard(ProductInfo(
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
    ))
}