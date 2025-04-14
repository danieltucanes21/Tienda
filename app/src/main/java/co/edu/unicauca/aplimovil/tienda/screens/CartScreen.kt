package edu.unicauca.apimovil.pixelplaza

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import co.edu.unicauca.aplimovil.tienda.viewModel.CartViewModel
import co.edu.unicauca.aplimovil.tienda.viewModel.NavigationViewModel

@Composable
fun CartScreen(
    initialProductList: List<ProductInfo>,
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = viewModel()
) {
    val cartUiState by cartViewModel.uiState.collectAsState()

    // Inicializar solo si está vacío
    LaunchedEffect(initialProductList) {
        if (cartUiState.products.isEmpty()) {
            cartViewModel.updateProductList(initialProductList)
        }
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
                ProductItem(product) {
                    cartViewModel.removeProduct(product)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        CartTotalSection(
            total = cartUiState.totalAmount,
            isLoading = cartUiState.isCheckoutProcessing,
            onCheckout = { cartViewModel.proceedToCheckout() }
        )

        if (cartUiState.isCheckoutComplete) {
            CheckoutCompleteDialog(
                onDismiss = {
                    cartViewModel.resetCheckoutState()
                    Navigator.navigateTo(Screen.Store.route)
                }
            )
        }
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

@Composable
fun CheckoutCompleteDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Compra completada") },
        text = { Text(text = "¡Gracias por tu compra!") },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(text = "Aceptar")
            }
        }
    )
}