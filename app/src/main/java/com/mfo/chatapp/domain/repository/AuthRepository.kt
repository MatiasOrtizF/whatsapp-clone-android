package com.mfo.chatapp.domain.repository

interface AuthRepository {

    suspend fun login(email: String, password: String): Boolean

    suspend fun signUp(email: String, password: String): Boolean
}