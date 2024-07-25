package com.mfo.chatapp.data.remote.repository

import com.google.firebase.auth.FirebaseAuth
import com.mfo.chatapp.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(email: String, password: String): String {
        return try {
            var userUID = ""
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { userUID = it.user?.uid?: "" }
                .await()
            userUID
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun signUp(email: String, password: String): String {
        return try {
            var userUID = ""
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { userUID = it.user?.uid?: "" }
                .await()
            userUID
        } catch (e: Exception) {
            ""
        }
    }
}