package co.edu.unicauca.aplimovil.tienda.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.PasarelaScreen
import co.edu.unicauca.aplimovil.tienda.ProductoDetallesScreen
import co.edu.unicauca.aplimovil.tienda.RegistroScreen
import co.edu.unicauca.aplimovil.tienda.components.ScreenWithAppBar
import co.edu.unicauca.aplimovil.tienda.screens.LoginScreen
import edu.unicauca.apimovil.pixelplaza.CartScreen
import edu.unicauca.apimovil.pixelplaza.ShoppingScreen
import edu.unicauca.apimovil.pixelplaza.StoreScreen
import edu.unicauca.apimovil.pixelplaza.generateData

@Composable
fun AppNavHost(navController : NavHostController,
               drawerState : DrawerState) {
    val productList = generateData()
    NavHost(navController = navController, startDestination = Screen.Store.route) {
        composable(Screen.Store.route) {
            ScreenWithAppBar(
                productList = productList,
                drawerState = drawerState,
                screen = { productList, modifier ->
                    StoreScreen(
                        productList = productList.toMutableList(),
                        modifier = modifier
                    )
                })
//            ShoppingScreen(productList = productList, navController = navController)
        }
        composable(Screen.Shopping.route) {
            ScreenWithAppBar(
                productList = productList,
                drawerState = drawerState,
                screen = { productList, modifier ->
                    ShoppingScreen(
                        productList = productList.toMutableList(),
                        modifier = modifier
                    )
                })
        }
        composable(Screen.Cart.route) {
            ScreenWithAppBar(
                productList = productList,
                drawerState = drawerState,
                screen = { productList, modifier ->
                    CartScreen(
                        productList = productList.toMutableList(),
                        modifier = modifier
                    )
                })
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Signup.route) {
            RegistroScreen(navController)
        }
        composable(Screen.Card.route) {
            PasarelaScreen(navController)
        }
        composable(Screen.DetailProduct.route) {
            ProductoDetallesScreen(navController)
        }
    }
}
