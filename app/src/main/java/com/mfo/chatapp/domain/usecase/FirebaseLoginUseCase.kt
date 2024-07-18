package com.mfo.chatapp.domain.usecase

import com.mfo.chatapp.domain.repository.AuthRepository
import com.mfo.chatapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        val loginState = authRepository.login(email, password)
        println(loginState)
        if (loginState) {
            emit(Resource.Success(true))
        } else {
            emit(Resource.Error("Login error"))
        }
    }
}