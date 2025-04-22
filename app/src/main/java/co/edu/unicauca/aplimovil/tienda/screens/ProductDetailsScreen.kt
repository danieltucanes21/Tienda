package co.edu.unicauca.aplimovil.tienda.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import co.edu.unicauca.aplimovil.tienda.viewModel.AppViewModelProvider
import co.edu.unicauca.aplimovil.tienda.viewModel.CartViewModel
import co.edu.unicauca.aplimovil.tienda.viewModel.ProductDetailViewModel
import coil.compose.rememberAsyncImagePainter
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.user
import java.sql.Date
import java.util.Calendar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import co.edu.unicauca.aplimovil.tienda.R

@Composable
fun ProductoDetallesScreen(
    userId: Int,
    productId: String,
    cartViewModel: CartViewModel = viewModel(factory = AppViewModelProvider.Factory),
    productDetailViewModel: ProductDetailViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onBack: () -> Unit
) {
    val uiState by productDetailViewModel.uiState.collectAsState()
    val context = LocalContext.current
    val tallas = listOf("XS", "S", "M", "L", "XL", "UN", "5", "7", "9", "11", "13", "15")

    // Cargar producto al iniciar
    LaunchedEffect(productId) {
        productDetailViewModel.loadProduct(productId)
    }

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (uiState.error != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = uiState.error!!, fontSize = 24.sp, color = Color.Red)
        }
        return
    }

    uiState.product?.let { producto ->
        // Diálogo para cantidad
        if (uiState.showQuantityDialog) {
            AlertDialog(
                onDismissRequest = { productDetailViewModel.showQuantityDialog(false) },
                title = { Text(stringResource(R.string.agregar_al_carrito)) },
                text = {
                    Column {
                        val selectedSize = uiState.selectedSize ?: "N/A"
                        Text(stringResource(R.string.selected_size_label, selectedSize))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(stringResource(R.string.ingresa_la_cantidad_deseada))
                        OutlinedTextField(
                            value = uiState.quantity.toString(),
                            onValueChange = { newValue ->
                                productDetailViewModel.updateQuantity(newValue.toIntOrNull() ?: 1)
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (userId == 0) {
                            Toast.makeText(context, context.getString(R.string.debes_iniciar_sesion), Toast.LENGTH_SHORT).show()
                            productDetailViewModel.showQuantityDialog(false)
                            return@Button
                        }

                        val productCart = ProductCart(
                            idOwner = userId,
                            product = producto,
                            quantity = uiState.quantity,
                            selectedSize = uiState.selectedSize!!,
                            dateAdded = Date(Calendar.getInstance().timeInMillis)
                        )
                        cartViewModel.addProduct(productCart)
                        productDetailViewModel.showQuantityDialog(false)
                        Toast.makeText(context,
                            context.getString(R.string.producto_agregado_al_carrito), Toast.LENGTH_SHORT).show()
                    }) {
                        Text(stringResource(R.string.confirmar))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { productDetailViewModel.showQuantityDialog(false) }) {
                        Text(stringResource(R.string.cancelar))
                    }
                }
            )
        }

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
            item {
                Image(
                    painter = rememberAsyncImagePainter(producto.painter),
                    contentDescription = producto.contentDescription,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
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

                    Text(text = stringResource(R.string.tallas), fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    // Grilla de Tallas
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(6),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {
                        items(tallas) { talla ->
                            Button(
                                onClick = { productDetailViewModel.updateSelectedSize(talla) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (uiState.selectedSize == talla) MaterialTheme.colorScheme.primary
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
                        text = stringResource(R.string.especificaciones),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.secondary,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(producto.specifications, color = MaterialTheme.colorScheme.onSecondary)
                            /*Text(stringResource(R.string.corte_ajustado), color = MaterialTheme.colorScheme.onSecondary)
                            Text(stringResource(R.string.cuello_redondo), color = MaterialTheme.colorScheme.onSecondary)
                            Text(stringResource(R.string._100_en_algod_n), color = MaterialTheme.colorScheme.onSecondary)*/
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.calificaciones),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(R.string._4_8),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (userId == 0) {
                                Toast.makeText(context,
                                    context.getString(R.string.debes_iniciar_sesion), Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (uiState.selectedSize == null) {
                                Toast.makeText(context, context.getString(R.string.selecciona_una_talla), Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            productDetailViewModel.showQuantityDialog(true)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = stringResource(R.string.agregar_al_carrito),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 18.sp
                        )
                    }
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
