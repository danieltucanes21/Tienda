package co.edu.unicauca.aplimovil.tienda.screens

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import co.edu.unicauca.aplimovil.tienda.models.Producto
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import co.edu.unicauca.aplimovil.tienda.viewModel.AppViewModelProvider
import co.edu.unicauca.aplimovil.tienda.viewModel.CartViewModel
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.PublicType
import edu.unicauca.apimovil.pixelplaza.Size
import coil.compose.rememberAsyncImagePainter
import edu.unicauca.apimovil.pixelplaza.user
import java.util.Calendar
import java.sql.Date

@Composable
fun ProductoDetallesScreen(
    productId: String,
    productList: List<ProductInfo>,
    cartViewModel: CartViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onBack: () -> Unit
) {
    val producto = productList.find { it.id.toString() == productId }
    var showQuantityDialog by remember { mutableStateOf(false) }
    var selectedSize by remember { mutableStateOf<String?>(null) }
    var quantity by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val tallas = listOf("XS", "S", "M", "L", "XL", "UN", "5", "7", "9", "11", "13", "15")

    if (producto == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Producto no encontrado", fontSize = 24.sp, color = Color.Red)
        }
        return
    }

    // Función para manejar el agregado al carrito
    fun handleAddToCart() {
        if (selectedSize == null) {
            Toast.makeText(context, "Por favor selecciona una talla", Toast.LENGTH_SHORT).show()
            return
        }
        showQuantityDialog = true
    }

    // Diálogo para cantidad
    if (showQuantityDialog) {
        AlertDialog(
            onDismissRequest = { showQuantityDialog = false },
            title = { Text("Agregar al carrito") },
            text = {
                Column {
                    Text("Talla seleccionada: ${selectedSize}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Ingresa la cantidad deseada:")
                    OutlinedTextField(
                        value = quantity.toString(),
                        onValueChange = { newValue ->
                            quantity = newValue.toIntOrNull()?.coerceAtLeast(1) ?: 1
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    val productCart = ProductCart(
                        idOwner = user.id,
                        product = producto,
                        quantity = quantity,
                        selectedSize = selectedSize!!,
                        dateAdded = Date(Calendar.getInstance().timeInMillis)
                    )
                    cartViewModel.addProduct(productCart)
                    showQuantityDialog = false
                    Toast.makeText(context, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showQuantityDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    LazyColumn(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        item {
            Image(
                painter = rememberAsyncImagePainter(producto.painter),
                contentDescription = producto.contentDescription,
                modifier = Modifier.fillMaxWidth().height(300.dp),
                contentScale = ContentScale.Crop
            )
        }

        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = producto.contentDescription,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "$${producto.price}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Tallas", fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(8.dp))

                // Grilla de Tallas
                LazyVerticalGrid(
                    columns = GridCells.Fixed(6),
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                ) {
                    items(tallas) { talla ->
                        Button(
                            onClick = { selectedSize = talla },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedSize == talla) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.secondary
                            ),
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = talla, color = MaterialTheme.colorScheme.onSecondary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "ESPECIFICACIONES",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Text("• Corte ajustado", color = MaterialTheme.colorScheme.onSecondary)
                        Text("• Cuello redondo", color = MaterialTheme.colorScheme.onSecondary)
                        Text("• 100% en algodón", color = MaterialTheme.colorScheme.onSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "CALIFICACIONES",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "4.8 ★★★★★",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { handleAddToCart() },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "Agregar al carrito",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewProductoDetallesScreen() {
//    val productList = listOf(
//        ProductInfo(
//            id = 1,
//            //painter = painterResource(id = R.drawable.ic_usuario),  // Cargar imagen como Painter
//            painter = "https://assets.dfb.de/uploads/000/289/444/custom_style_1_mitera_hannah-ursula.jpg?1692690626",
//            contentDescription = "Producto 1",
//            description = "Descripción del Producto 1",
//            price = 100.0,
//            color = "Negro",
//            brand = "Marca X",
//            sizes = listOf(Size.S, Size.M, Size.L),
//            specifications = "Corte ajustado, 100% algodón",
//            score = 5,
//            publicType = PublicType.MEN
//        ),
//        ProductInfo(
//            id = 2,
//            //painter = painterResource(id = R.drawable.ic_usuario),
//            painter = "https://th.bing.com/th/id/OIP.UAiTfjoZB_J1ISwqrzhgWgHaHa?w=481&h=481&rs=1&pid=ImgDetMain",
//            contentDescription = "Producto 2",
//            description = "Descripción del Producto 2",
//            price = 200.0,
//            color = "Blanco",
//            brand = "Marca Y",
//            sizes = listOf(Size.M, Size.L, Size.XL),
//            specifications = "Cómodo y transpirable",
//            score = 4,
//            publicType = PublicType.WOMEN
//        )
//    )
//
//    ProductoDetallesScreen(productId = "1", productList = productList) {}
//}

