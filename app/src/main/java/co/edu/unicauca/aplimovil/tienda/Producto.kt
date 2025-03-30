package co.edu.unicauca.aplimovil.tienda


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.unicauca.aplimovil.tienda.R

// Modelo de product
data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val imagen: Int
)

// Lista de productos (simulada)
val productos = listOf(
    Producto(1, "Playera negra", 228.0, R.drawable.ic_producto),
    //Producto(2, "Playera blanca", 199.0, R.drawable.producto2),

)

@Composable
fun ProductosScreen(onProductoClick: (Producto) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Text(
            text = "Productos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Grilla de 2 columnas
            modifier = Modifier.padding(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(productos) { producto ->
                ProductoItem(producto) {
                    onProductoClick(producto)
                }
            }
        }
    }
}

@Composable
fun ProductoItem(producto: Producto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Column {
            Image(
                painter = painterResource(id = producto.imagen),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = producto.nombre,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "$${producto.precio}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7B5CF0),
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}
@Preview
@Composable
fun PreviewProductosScreen() {
    ProductosScreen(onProductoClick = {})
}
