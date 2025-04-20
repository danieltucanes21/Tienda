package co.edu.unicauca.aplimovil.tienda.viewModel

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import co.edu.unicauca.aplimovil.tienda.TiendaApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            RegisterViewModel(
                usersRepository = tiendaApplication().container.usersRepository
            )
        }
        initializer {
            LoginViewModel(
                repository = tiendaApplication().container.usersRepository
            )
        }
        initializer {
            ProductViewModel(
                productRepository = tiendaApplication().container.productRepository
            )
        }
        initializer {
            CartViewModel(
                cartItemRepository = tiendaApplication().container.cartItemRepository,
                purchaseHistoryRepository = tiendaApplication().container.purchaseHistoryRepository,
                productRepository = tiendaApplication().container.productRepository
            )
        }
        initializer {
            ShoppingViewModel(
                purchaseHistoryRepository = tiendaApplication().container.purchaseHistoryRepository,
                productRepository = tiendaApplication().container.productRepository
            )
        }
        initializer {
            ProductDetailViewModel (
                productRepository = tiendaApplication().container.productRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [23wsaTiendaApplication].
 */
fun CreationExtras.tiendaApplication(): TiendaApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TiendaApplication)
