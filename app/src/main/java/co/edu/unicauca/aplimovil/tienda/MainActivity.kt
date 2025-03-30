package co.edu.unicauca.aplimovil.tienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.components.NavigationDrawer
import co.edu.unicauca.aplimovil.tienda.components.ScreenWithAppBar
import co.edu.unicauca.aplimovil.tienda.navigation.AppNavHost
import co.edu.unicauca.aplimovil.tienda.ui.theme.TiendaTheme
import edu.unicauca.apimovil.pixelplaza.StoreScreen
import edu.unicauca.apimovil.pixelplaza.generateData

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
    }
}

@Composable
fun PixelPlazaScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.inicio), // Reemplázalo con tu URL de imagen
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "PixelPlaza",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = R.drawable.mindfulness2), // Reemplázalo con tu recurso
                contentDescription = "Logo",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Boutique",
                fontSize = 20.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Acción de Ingresar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Ingresar", color = Color.White)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { /* Acción de Registrarse */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B1FA2)), // Morado
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Registrarme", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun TiendaApp() {
    var productoSeleccionado by remember { mutableStateOf<Producto?>(null) }

    if (productoSeleccionado == null) {
        ProductosScreen(onProductoClick = { productoSeleccionado = it })
    }

}

@Preview(showBackground = true)
@Composable
fun PixelPlazaScreenPreview() {
    PixelPlazaScreen()
}
