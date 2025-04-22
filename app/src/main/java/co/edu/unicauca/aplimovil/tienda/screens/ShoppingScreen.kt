package edu.unicauca.apimovil.pixelplaza

import android.widget.Button
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.R
//import co.edu.unicauca.aplimovil.tienda.NavigationDrawer
import co.edu.unicauca.aplimovil.tienda.components.ProductItem
import co.edu.unicauca.aplimovil.tienda.components.NavigationDrawer
import co.edu.unicauca.aplimovil.tienda.components.PixelPlazaAppBar
import co.edu.unicauca.aplimovil.tienda.components.PixelPlazaScaffold
import co.edu.unicauca.aplimovil.tienda.components.ScreenWithAppBar
import co.edu.unicauca.aplimovil.tienda.models.ProductBuy
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import co.edu.unicauca.aplimovil.tienda.navigation.Screens
import co.edu.unicauca.aplimovil.tienda.viewModel.AppViewModelProvider
import co.edu.unicauca.aplimovil.tienda.viewModel.ShoppingViewModel
import kotlinx.coroutines.launch

@Composable
fun ShoppingScreen(
    userId: Int,
    modifier: Modifier = Modifier,
    shoppingViewModel: ShoppingViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by shoppingViewModel.uiState.collectAsState()

    // Initialize products
    LaunchedEffect(userId) {
        shoppingViewModel.loadProducts(userId)
    }

    // Handle loading state
    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title with refresh button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.compras),
                fontSize = textTitleLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            IconButton(
                onClick = { shoppingViewModel.refresh(userId) },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = stringResource(R.string.refrescar),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        // Empty state
        if (uiState.isEmpty) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.bolsa_vac_a),
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.error ?: stringResource(R.string.no_hay_compras_realizadas),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        } else {
            // List of products
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(uiState.products) { product ->
                    ProductItem(
                        product = product.product
                    ) {
                        shoppingViewModel.removeProduct(product)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun PreviewShoppingScreen() {
//    val productCart = generateCart(user)
//    val productBuy = generateBuy(user, productCart)
//    val navController = rememberNavController()
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//
//    NavigationDrawer(navController = navController, drawerState = drawerState) {
//        ScreenWithAppBar(productList = productBuy,
//            drawerState = drawerState,
//            screen = { productList, modifier ->
//                ShoppingScreen(
//                    initialBuyList = productList.toMutableList(),
//                    modifier = modifier
//                )
//        })
//    }
//}