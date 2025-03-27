package co.edu.unicauca.aplimovil.tienda.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.edu.unicauca.aplimovil.tienda.R
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixelPlazaAppBar(
    drawerState: DrawerState,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = { Text("Pixel Plaza") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menú",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixelPlazaScaffold(
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    drawerState: DrawerState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(enabled = drawerState.isOpen) {
                scope.launch { drawerState.close() }
            }
    ) {
        Scaffold(
            topBar = topBar,
            content = content
        )
    }
}

//This composable let you combine a screen with de app bar
@Composable
fun ScreenWithAppBar(
    productList: List<ProductInfo>,
    drawerState: DrawerState,
    screen: @Composable (List<ProductInfo>, Modifier) -> Unit
) {
    val scope = rememberCoroutineScope()

    PixelPlazaScaffold(
        topBar = {
            PixelPlazaAppBar(
                drawerState = drawerState,
                onMenuClick = { scope.launch { drawerState.open() } }
            )
        },
        drawerState = drawerState,
        content = { innerPadding ->
            screen(
                productList.toMutableList(),
                Modifier.padding(innerPadding)
            )
        }
    )
}