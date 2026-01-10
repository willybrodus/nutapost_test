package com.nutapos.nutatest.core.domain.usecase.session

import com.nutapos.nutatest.core.data.session.SessionManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoggedInUserIdUseCase @Inject constructor(private val sessionManager: SessionManager) {
    operator fun invoke(): Flow<Long?> = sessionManager.loggedInUserIdFlow
}

class SetLoggedInUserIdUseCase @Inject constructor(private val sessionManager: SessionManager) {
    suspend operator fun invoke(userId: Long?) = sessionManager.setLoggedInUserId(userId)
}
