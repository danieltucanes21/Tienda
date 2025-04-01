package co.edu.unicauca.aplimovil.tienda.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.edu.unicauca.aplimovil.tienda.screens.PasarelaScreen
import co.edu.unicauca.aplimovil.tienda.screens.ProductoDetallesScreen
import co.edu.unicauca.aplimovil.tienda.RegistroScreen
import co.edu.unicauca.aplimovil.tienda.components.ScreenWithAppBar
import co.edu.unicauca.aplimovil.tienda.screens.LoginScreen
import co.edu.unicauca.aplimovil.tienda.screens.PixelPlazaScreen
import co.edu.unicauca.aplimovil.tienda.viewModel.NavigationViewModel
import edu.unicauca.apimovil.pixelplaza.CartScreen
import edu.unicauca.apimovil.pixelplaza.ShoppingScreen
import edu.unicauca.apimovil.pixelplaza.StoreScreen
import edu.unicauca.apimovil.pixelplaza.generateData
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    drawerState: DrawerState,
    viewModel: NavigationViewModel = viewModel()
) {
    Navigator.initialize(viewModel)

    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collect { route ->
            navController.navigate(route) {
                launchSingleTop = true
            }
        }
    }
    val productList = generateData()
    NavHost(navController = navController, startDestination = Screen.Start.route)

    {
        composable(Screen.Start.route) {
            PixelPlazaScreen(navController)
        }
        composable(Screen.Store.route) {
            ScreenWithAppBar(
                productList = productList,
                drawerState = drawerState,
                screen = { productList, modifier ->
                    StoreScreen(
                        productList = productList.toMutableList(),
                        modifier = modifier,
                        navController = navController
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
                        modifier = modifier,
                        onClickButton = {
                            navController.navigate(Screen.Store.route)
                        }
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
        composable(Screen.SignUp.route) {
            RegistroScreen(navController)
        }
        composable(Screen.Card.route) {
            PasarelaScreen(navController)
        }
        composable("DetailProduct/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            ProductoDetallesScreen(productId = productId, productList = productList) {
                navController.popBackStack()
            }
        }


    }
}
