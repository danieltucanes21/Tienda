package edu.unicauca.apimovil.pixelplaza

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.edu.unicauca.aplimovil.tienda.components.ProductItem
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import co.edu.unicauca.aplimovil.tienda.navigation.Navigator
import co.edu.unicauca.aplimovil.tienda.navigation.Screen
import co.edu.unicauca.aplimovil.tienda.viewModel.AppViewModelProvider
import co.edu.unicauca.aplimovil.tienda.viewModel.CartViewModel

@Composable
fun CartScreen(
    userId: Int,
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val cartUiState by cartViewModel.uiState.collectAsState()

    // Inicializar solo si está vacío
    LaunchedEffect(userId) {
        cartViewModel.loadCartItems(userId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Carrito (${cartUiState.itemCount} items)",
            fontSize = textTitleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartUiState.products) { product ->
                ProductItem(product.product) {
                    Log.i("App", "Producto a eliminar: $product")
                    cartViewModel.removeProduct(product)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        CartTotalSection(
            total = cartUiState.totalAmount,
            isLoading = cartUiState.isCheckoutProcessing,
            onCheckout = { // cartViewModel.proceedToCheckout(userId = userId)
                Navigator.navigateTo(Screen.Card.route) }

        )

//        if (cartUiState.isCheckoutComplete) {
//            CheckoutCompleteDialog(
//                onDismiss = {
//                    cartViewModel.resetCheckoutState()
//                    Navigator.navigateTo(Screen.Store.route)
//                }
//            )
//        }
    }
}

@Composable
fun CartTotalSection(
    total: Double,
    isLoading: Boolean,
    onCheckout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Monto total:",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = textLabelLarge,
                fontFamily = MaterialTheme.typography.labelLarge.fontFamily
            )
            Text(
                text = "$${"%.2f".format(total)}",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = textLabelLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = MaterialTheme.typography.labelLarge.fontFamily
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onCheckout,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading && total > 0
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text(text = "Realizar compra", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}