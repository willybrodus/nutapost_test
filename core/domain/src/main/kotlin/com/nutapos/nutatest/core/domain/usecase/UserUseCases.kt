package com.nutapos.nutatest.core.domain.usecase

import com.nutapos.nutatest.core.local.entity.User
import com.nutapos.nutatest.core.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<List<User>> = userRepository.getAllUsers()
}

class GetUserByIdUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(id: Long): Flow<User?> = userRepository.getUserById(id)
}

class InsertUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) = userRepository.insertUser(user)
}

class UpdateUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) = userRepository.updateUser(user)
}

class DeleteUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) = userRepository.deleteUser(user)
}
