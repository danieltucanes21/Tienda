package co.edu.unicauca.aplimovil.tienda.data

import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    /**
     * Retrieve all the users from the the given data source.
     */
    fun getAllUsersStream(): Flow<List<User>>

    /**
     * Retrieve an user from the given data source that matches with the [id].
     */
    fun getUserStream(id: Int): Flow<User?>

    /**
     * Insert user in the data source
     */
    suspend fun insertUser(user: User)

    /**
     * Delete user from the data source
     */
    suspend fun deleteUser(user: User)

    /**
     * Update user in the data source
     */
    suspend fun updateUser(user: User)


    suspend fun login(email: String, password: String) :Boolean

    suspend fun login2(email: String, password: String) :User?
}