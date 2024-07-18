package com.mfo.chatapp.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.mfo.chatapp.data.remote.Constants.DEFAULT_NETWORK_ERROR
import com.mfo.chatapp.domain.model.Message
import com.mfo.chatapp.domain.repository.MessageRepository
import com.mfo.chatapp.utils.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseMessagesRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : MessageRepository {

    override suspend fun sendMessage(message: Message): Resource<Unit> {
        return try {
            var isSuccessful = false
            firestore.collection(message.chatId)
                .document(message.id)
                .set(message, SetOptions.merge())
                .addOnCompleteListener { isSuccessful = it.isSuccessful }
                .await()

            if(isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error(DEFAULT_NETWORK_ERROR)
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    /*override suspend fun fetchMessagesByChat(chatId: String): Resource<List<Message>> {
        TODO("Not yet implemented")
    }*/
}