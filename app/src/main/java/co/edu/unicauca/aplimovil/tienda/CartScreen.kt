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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CartScreenPreview () {

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
        CartScreen(
            productList = productList,
            modifier = Modifier.padding(innerPadding) // Aquí aplicamos el padding
        )
    }
}


@Composable
fun CartScreen(productList: MutableList<ProductInfo>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp) // Padding adicional para el contenido
    ) {
        // Título
        Text(
            text = "Carrito",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 14.sp,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally),
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


@Composable
fun CartTotalSection(total: Double) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Monto total:", color = Color.White, fontSize = 18.sp)
            Text(text = "$$total", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* TODO: Acción de compra */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Realizar compra", color = Color.White)
        }
    }
}


//tipos de letra conforma y robototo