package co.edu.unicauca.aplimovil.tienda.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.grid.items
import co.edu.unicauca.aplimovil.tienda.models.Producto
import co.edu.unicauca.aplimovil.tienda.R
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.PublicType
import edu.unicauca.apimovil.pixelplaza.Size

@Composable
fun ProductoDetallesScreen(productId: String, productList: List<ProductInfo>, onBack: () -> Unit) {
    val producto = productList.find { it.id.toString() == productId } // Buscar producto por ID

    if (producto != null) {
        var tallaSeleccionada by remember { mutableStateOf<String?>(null) }
        val tallas = listOf("XS", "S", "M", "L", "XL", "UN", "5", "7", "9", "11", "13", "15")

        LazyColumn(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            item {
                Image(
                    painter = producto.painter,
                    contentDescription = producto.contentDescription,
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = producto.contentDescription, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "$${producto.price}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF7B5CF0))

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Tallas", fontSize = 18.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))

                    // **Grilla de Tallas**
                    LazyVerticalGrid(columns = GridCells.Fixed(6), modifier = Modifier.fillMaxWidth().height(100.dp)) {
                        items(tallas) { talla ->
                            Button(
                                onClick = { tallaSeleccionada = talla },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (tallaSeleccionada == talla) Color(0xFF7B5CF0) else Color.DarkGray
                                ),
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Text(text = talla, color = Color.White)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("ESPECIFICACIONES", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.DarkGray, RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    ) {
                        Column {
                            Text("• Corte ajustado", color = Color.White)
                            Text("• Cuello redondo", color = Color.White)
                            Text("• 100% en algodón", color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("CALIFICACIONES", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    Text("4.8 ★★★★★", fontSize = 16.sp, color = Color(0xFF7B5CF0))

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { /* Agregar al carrito */ },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B5CF0))
                    ) {
                        Text("Agregar al carrito", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    } else {
        // Si no se encuentra el producto
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Producto no encontrado", fontSize = 24.sp, color = Color.Red)
        }
    }
}

@Preview
@Composable
fun PreviewProductoDetallesScreen() {
    val productList = listOf(
        ProductInfo(
            id = 1,
            painter = painterResource(id = R.drawable.ic_usuario),  // Cargar imagen como Painter
            contentDescription = "Producto 1",
            description = "Descripción del Producto 1",
            price = 100.0,
            color = "Negro",
            brand = "Marca X",
            sizes = listOf(Size.S, Size.M, Size.L),
            specifications = "Corte ajustado, 100% algodón",
            score = 5,
            publicType = PublicType.MEN
        ),
        ProductInfo(
            id = 2,
            painter = painterResource(id = R.drawable.ic_usuario),
            contentDescription = "Producto 2",
            description = "Descripción del Producto 2",
            price = 200.0,
            color = "Blanco",
            brand = "Marca Y",
            sizes = listOf(Size.M, Size.L, Size.XL),
            specifications = "Cómodo y transpirable",
            score = 4,
            publicType = PublicType.WOMEN
        )
    )

    ProductoDetallesScreen(productId = "1", productList = productList) {}
}

