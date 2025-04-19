package edu.unicauca.apimovil.pixelplaza

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.components.NavigationDrawer
import co.edu.unicauca.aplimovil.tienda.components.ProductItemGridCard
import co.edu.unicauca.aplimovil.tienda.components.ScreenWithAppBar
import co.edu.unicauca.aplimovil.tienda.components.SearchBar
import co.edu.unicauca.aplimovil.tienda.navigation.Navigator
import co.edu.unicauca.aplimovil.tienda.navigation.Screen
import co.edu.unicauca.aplimovil.tienda.viewModel.AppViewModelProvider
import co.edu.unicauca.aplimovil.tienda.viewModel.CartViewModel
import co.edu.unicauca.aplimovil.tienda.viewModel.ProductViewModel
import co.edu.unicauca.aplimovil.tienda.viewModel.StoreViewModel


@Preview
@Composable
fun StoreScreenPreview () {
    val productList = generateData()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    NavigationDrawer(navController = navController, drawerState = drawerState) {
        ScreenWithAppBar(productList = productList,
            drawerState = drawerState,
            screen = { productList, modifier ->
                StoreScreen(
                    //productList = productList.toMutableList(),
                    modifier = modifier,
//                    navController = navController
                )
            })
    }
}

@Composable
fun StoreScreen(
    //productList: List<ProductInfo>,
    modifier: Modifier = Modifier,
    storeViewModel: StoreViewModel = viewModel(),
    productViewModel: ProductViewModel = viewModel(factory = AppViewModelProvider.Factory),
    cartViewModel: CartViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = productViewModel.productUiState
    val categories = listOf(
        "Todos" to null,
        "Mujeres" to PublicType.WOMEN,
        "Hombres" to PublicType.MEN,
        "Niños" to PublicType.CHILD
    )

    // Initialize once
    LaunchedEffect(Unit) {
        if (uiState.products.isEmpty()) {
            //storeViewModel.initialize(productList)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        // Search bar
        SearchBar(
            query = uiState.searchQuery,
            onQueryChange = { productViewModel.search(it) },
            onClear = {productViewModel.search("") },
            onProfileClick = { Navigator.navigateTo(Screen.Login.route) }
        )

        // Category tabs
        TabRow(
            selectedTabIndex = categories.indexOfFirst { it.second == uiState.selectedCategory }.coerceAtLeast(0),
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            categories.forEachIndexed { index, (title, category) ->
                Tab(
                    selected = uiState.selectedCategory == category,
                    onClick = { productViewModel.filterByCategory(category) },
                    text = {
                        Text(
                            title,
                            color = if (uiState.selectedCategory == category) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            }
                        )
                    }
                )
            }
        }

        // Product grid
        when {
            uiState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(uiState.error, color = MaterialTheme.colorScheme.error)
                }
            }
            uiState.filteredProducts.isEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No se encontraron productos", style = MaterialTheme.typography.bodyLarge)
                }
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(8.dp)
                ) {
                    items(uiState.filteredProducts) { product ->
                        ProductItemGridCard(
                            product = product,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .aspectRatio(0.75f),
                            onImageClick = { productId ->
                                Navigator.navigateTo("DetailProduct/$productId")
                            },
                            onAddClick = {cartViewModel.addProduct(it)}
                        )
                    }
                }
            }
        }
    }
}