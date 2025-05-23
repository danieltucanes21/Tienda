package co.edu.unicauca.aplimovil.tienda.data

import kotlinx.coroutines.flow.Flow

class OfflineUsersRepository (private val userDao: UserDao) : UsersRepository {
    override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()

    override fun getUserStream(id: Int): Flow<User?> = userDao.getUser(id)


    override suspend fun insertUser(user: User) = userDao.insert(user)


    override suspend fun deleteUser(user: User) = userDao.delete(user)


    override suspend fun updateUser(user: User) = userDao.update(user)

    override suspend fun login(email: String, password: String): Boolean {
        val user = userDao.login(email, password)
        return user != null
    }

    override suspend fun login2(email: String, password: String): User? {
        return userDao.login(email, password)
    }

}