package com.mfo.chatapp.domain.repository

import com.mfo.chatapp.domain.model.Message
import com.mfo.chatapp.utils.Resource

interface MessageRepository {

    suspend fun sendMessage(message: Message): Resource<Unit>

    //suspend fun fetchMessagesByChat(chatId: String): Resource<List<Message>>
}