package com.mfo.chatapp.domain.repository

interface AuthRepository {

    suspend fun login(email: String, password: String): String

    suspend fun signUp(email: String, password: String): String
}