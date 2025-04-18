package co.edu.unicauca.aplimovil.tienda.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val usersRepository: UsersRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [UsersRepository]
     */
    override val usersRepository: UsersRepository by lazy {
        OfflineUsersRepository(AppDatabase.getDatabase(context).userDao())
    }
}