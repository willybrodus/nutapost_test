package com.nutapos.nutatest.core.domain.usecase.user

import com.nutapos.nutatest.core.local.entity.User as Entity
import com.nutapos.nutatest.core.domain.model.user.User
import com.nutapos.nutatest.core.data.repository.UserRepository
import com.nutapos.nutatest.core.domain.model.user.toDomainModel
import com.nutapos.nutatest.core.domain.model.user.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<List<User>> = userRepository.getAllUsers().map { users -> users.map { it.toDomainModel() }  }
}

class GetUserLogin @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(id: Long): Flow<User?> = userRepository.getUserLogin().map { it?.toDomainModel() }
}

class InsertUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) = userRepository.insertUser(user.toEntity())
}

class UpdateUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) = userRepository.updateUser(user.toEntity())
}
