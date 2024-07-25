package com.mfo.chatapp.domain.repository

import com.mfo.chatapp.domain.model.User

interface UserRepository {
    suspend fun createUser(user: User): Boolean
    suspend fun getUser(uid: String): User
}