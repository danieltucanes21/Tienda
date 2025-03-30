package co.edu.unicauca.aplimovil.tienda

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.screens.ProductoDetallesScreen
import edu.unicauca.apimovil.pixelplaza.StoreScreen
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.generateData


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val productList = generateData().map { convertToProducto(it) }
    NavHost(navController = navController, startDestination = Screens.StoreScreen.name) {
        composable(route = Screens.StoreScreen.name) {
            StoreScreen(productList = generateData(), navController = navController)
        }
        composable("productDetails/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            ProductoDetallesScreen(productId = productId, productList = productList) {
                navController.popBackStack()
            }
        }

    }
}

// Define las pantallas en un enum
enum class Screens {
    StoreScreen,
    ProductDetailsScreen
}
@Composable
fun convertToProducto(productInfo: ProductInfo): Producto {
    val imageResourceId = when (productInfo.painter) {
        painterResource(R.drawable.image_list_1) -> R.drawable.image_list_1
        painterResource(R.drawable.image_list_2) -> R.drawable.image_list_2
        painterResource(R.drawable.image_list_3) -> R.drawable.image_list_3
        painterResource(R.drawable.image_list_4) -> R.drawable.image_list_4
        painterResource(R.drawable.image_list_5) -> R.drawable.image_list_5
        painterResource(R.drawable.image_list_6) -> R.drawable.image_list_6
        painterResource(R.drawable.image_list_7) -> R.drawable.image_list_7
        painterResource(R.drawable.image_list_8) -> R.drawable.image_list_8
        else -> R.drawable.ic_usuario // Default image resource
    }
    return Producto(
        id = productInfo.id,
        nombre = productInfo.contentDescription,
        precio = productInfo.price,
        imagen = imageResourceId
    )
}
