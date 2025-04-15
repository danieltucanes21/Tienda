package co.edu.unicauca.aplimovil.tienda.navigation

import co.edu.unicauca.aplimovil.tienda.viewModel.NavigationViewModel

object Navigator {
    private var viewModel: NavigationViewModel? = null

    fun initialize(viewModel: NavigationViewModel) {
        this.viewModel = viewModel
    }

    fun navigateTo(route: String) {
        viewModel?.navigateTo(route)
    }
}