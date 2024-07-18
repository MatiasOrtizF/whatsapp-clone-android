package com.mfo.chatapp.domain.usecase

import com.mfo.chatapp.data.remote.Constants.DEFAULT_NETWORK_ERROR
import com.mfo.chatapp.domain.model.Message
import com.mfo.chatapp.domain.repository.MessageRepository
import com.mfo.chatapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: MessageRepository
) {
    suspend operator fun invoke(message: Message): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)

        val networkRequest = repository.sendMessage(message = message)

        when(networkRequest) {
            is Resource.Success -> emit(Resource.Success(Unit))
            is Resource.Error -> emit(Resource.Error(networkRequest.message))
            else -> emit(Resource.Error(DEFAULT_NETWORK_ERROR))
        }
    }
}