package com.mfo.chatapp.di

import com.mfo.chatapp.data.remote.FirebaseAuthRepositoryImp
import com.mfo.chatapp.data.remote.FirebaseMessagesRepositoryImpl
import com.mfo.chatapp.domain.repository.AuthRepository
import com.mfo.chatapp.domain.repository.MessageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(firebaseAuthRepositoryImp: FirebaseAuthRepositoryImp): AuthRepository

    @Binds
    abstract fun bindMessagesRepository(messagesRepository: FirebaseMessagesRepositoryImpl): MessageRepository
}