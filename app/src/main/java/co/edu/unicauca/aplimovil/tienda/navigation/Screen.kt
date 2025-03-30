package co.edu.unicauca.aplimovil.tienda.navigation

sealed class Screen(val route: String) {
    object Cart : Screen("cart")
    object Store : Screen("store")
    object Shopping : Screen("shopping")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Card : Screen("card")
    object DetailProduct : Screen("detailProduct")
}