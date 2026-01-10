package com.nutapos.nutatest.core.data.repository

import com.nutapos.nutatest.core.local.dao.UserDao
import com.nutapos.nutatest.core.local.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    fun getUserLogin(): Flow<User?>
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
    override fun getUserLogin(): Flow<User?> = userDao.getUserLogin()
    override suspend fun insertUser(user: User) = userDao.insertUser(user)
    override suspend fun updateUser(user: User) = userDao.updateUser(user)
    override suspend fun deleteUser(user: User) = userDao.deleteUser(user)
}
