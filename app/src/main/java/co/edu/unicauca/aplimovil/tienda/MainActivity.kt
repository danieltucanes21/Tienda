package co.edu.unicauca.aplimovil.tienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.components.NavigationDrawer
import co.edu.unicauca.aplimovil.tienda.data.AppDatabase
import co.edu.unicauca.aplimovil.tienda.navigation.AppNavHost
import co.edu.unicauca.aplimovil.tienda.ui.theme.TiendaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TiendaTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                NavigationDrawer(
                    navController = navController,
                    drawerState = drawerState
                ) {
                    AppNavHost(navController = navController, drawerState = drawerState)
                }
            }
        }
        // Bloque de depuración para verificar los productos en la base de datos
        CoroutineScope(Dispatchers.IO).launch {
            val productDao = AppDatabase.getDatabase(applicationContext).productDao()
            val products = productDao.getAllProducts()
            println("Productos en la base de datos: $products")
        }
    }
}
