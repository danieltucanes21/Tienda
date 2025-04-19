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
import co.edu.unicauca.aplimovil.tienda.viewModel.CartViewModel
import co.edu.unicauca.aplimovil.tienda.viewModel.ShoppingViewModel
import co.edu.unicauca.aplimovil.tienda.viewModel.StoreViewModel
import edu.unicauca.apimovil.pixelplaza.generateBuy
import edu.unicauca.apimovil.pixelplaza.generateCart
import edu.unicauca.apimovil.pixelplaza.user

@Composable
fun AppNavHost(
    navController: NavHostController,
    drawerState: DrawerState,
    viewModel: NavigationViewModel = viewModel()
) {
    val shoppingViewModel: ShoppingViewModel = viewModel()
    Navigator.initialize(viewModel)

    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collect { route ->
            navController.navigate(route) {
                launchSingleTop = true
            }
        }
    }
    val productList = generateData()

    val user = user
    val cartList = generateCart(user)
    val buyList = generateBuy(user, cartList)

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
                        //productList = productList.toMutableList(),
                        modifier = modifier,
//                        navController = navController
                    )
                })
        }
        composable(Screen.Shopping.route) {
            ScreenWithAppBar(
                productList = buyList,
                drawerState = drawerState,
                screen = { buyList, modifier ->
                    ShoppingScreen(
                        initialBuyList = buyList.toMutableList(),
                        modifier = modifier,
                        shoppingViewModel = shoppingViewModel
                    )
                })
        }
        composable(Screen.Cart.route) {
            ScreenWithAppBar(
                productList = cartList,
                drawerState = drawerState,
                screen = { cartList, modifier ->
                    CartScreen(
                        userId = user.id,
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
