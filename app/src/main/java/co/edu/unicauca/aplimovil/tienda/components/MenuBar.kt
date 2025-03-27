package co.edu.unicauca.aplimovil.tienda.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    navController: NavHostController,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf(0) }

    val onItemClick: (Int) -> Unit = { index ->
        selectedItem = index
        scope.launch { drawerState.close() }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                ModalDrawerSheet(
                    modifier = Modifier
                        .width(72.dp)
                        .height(280.dp),
                    drawerShape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                    drawerContainerColor = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        NavigationIcon(
                            icon = Icons.Default.Home,
                            selected = selectedItem == 0,
                            onClick = {
                                onItemClick(0)
                                navController.navigate("home")
                            }
                        )
                        NavigationIcon(
                            icon = Icons.Default.ShoppingCart,
                            selected = selectedItem == 1,
                            onClick = {
                                onItemClick(1)
                                navController.navigate("cart")
                            }
                        )
                        NavigationIcon(
                            icon = Icons.Default.Check,
                            selected = selectedItem == 2,
                            onClick = {
                                onItemClick(2)
                                navController.navigate("purchaseHistory")
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        NavigationIcon(
                            icon = Icons.Default.CheckCircle,
                            selected = selectedItem == 3,
                            onClick = { onItemClick(3) }
                        )
                    }
                }
            }
        }
    ) {
        content()
    }
}

// 🔹 Composable para el ícono del menú sin texto
@Composable
fun NavigationIcon(
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary
        )
    }
}