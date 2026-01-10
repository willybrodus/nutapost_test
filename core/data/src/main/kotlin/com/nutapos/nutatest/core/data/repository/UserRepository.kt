package com.nutapos.nutatest.core.data.repository

import com.nutapos.nutatest.core.local.dao.UserDao
import com.nutapos.nutatest.core.local.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    fun getUserById(id: Long): Flow<User?>
    suspend fun insertUser(user: User): Long
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
    override fun getUserById(id: Long): Flow<User?> = userDao.getUserById(id)
    override suspend fun insertUser(user: User): Long = userDao.insert(user)
    override suspend fun updateUser(user: User) = userDao.update(user)
    override suspend fun deleteUser(user: User) = userDao.delete(user)
}
