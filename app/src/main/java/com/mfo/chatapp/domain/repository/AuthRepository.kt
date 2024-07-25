package com.mfo.chatapp.domain.repository

import com.mfo.chatapp.domain.model.User

interface AuthRepository {

    suspend fun login(email: String, password: String): String

    suspend fun signUp(email: String, password: String): String
}