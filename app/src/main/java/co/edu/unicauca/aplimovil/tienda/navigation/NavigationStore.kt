package co.edu.unicauca.aplimovil.tienda.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.models.Producto
import co.edu.unicauca.aplimovil.tienda.screens.ProductoDetallesScreen
import edu.unicauca.apimovil.pixelplaza.StoreScreen
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.generateData
import edu.unicauca.apimovil.pixelplaza.user
import kotlin.collections.map


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val productList = generateData()

    NavHost(navController = navController, startDestination = Screens.StoreScreen.name) {
        composable(route = Screens.StoreScreen.name) {
            //StoreScreen(productList = productList, navController = navController)
//            StoreScreen(navController = navController)
        }
        composable("productDetails/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            ProductoDetallesScreen(user.id, productId = productId) {
                navController.popBackStack()
            }
        }
    }
}


@Composable
fun convertToProducto(productInfo: ProductInfo): Producto {
    val imageResourceId = when (productInfo.painter) {
        "https://assets.dfb.de/uploads/000/289/444/custom_style_1_mitera_hannah-ursula.jpg?1692690626" -> R.drawable.image_list_1
        "https://th.bing.com/th/id/OIP.UAiTfjoZB_J1ISwqrzhgWgHaHa?w=481&h=481&rs=1&pid=ImgDetMain" -> R.drawable.image_list_2
        "https://th.bing.com/th/id/OIP.73sQuBGcuzn0EVEmGvKlZAHaH5?w=900&h=960&rs=1&pid=ImgDetMain" -> R.drawable.image_list_3
        "https://earthfullyliving.com/wp-content/uploads/2022/08/extending-clothes-life-sustainable-fashion-200x300.jpg" -> R.drawable.image_list_4
        "https://images.fashionmodeldirectory.com/images/models/74993/lulu-reynolds-557589-squaremedium.jpg" -> R.drawable.image_list_5
        "https://images.unsplash.com/photo-1590799822755-4ec061a6b2a5?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" -> R.drawable.image_list_6
        "https://i.scdn.co/image/ab67616d00001e02e254f7cd902eda979b28c012" -> R.drawable.image_list_7
        "https://jm-moda.si/wp-content/uploads/2019/05/16-2-2-256x256.jpg" -> R.drawable.image_list_8
        else -> R.drawable.ic_usuario // Imagen por defecto
    }
    return Producto(
        id = productInfo.id,
        nombre = productInfo.contentDescription,
        precio = productInfo.price,
        imagen = imageResourceId
    )
}
