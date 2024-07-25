package com.mfo.chatapp.data.remote.repository

import com.google.firebase.auth.FirebaseAuth
import com.mfo.chatapp.domain.repository.AuthRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseAuthRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(email: String, password: String): String {
        return try {
            suspendCancellableCoroutine { continuation ->
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener { result ->
                        val userUID = result.user?.uid ?: ""
                        continuation.resume(userUID)
                    }
                    .addOnFailureListener { exception ->
                        continuation.resumeWithException(exception)
                    }
            }
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun signUp(email: String, password: String): String {
        return try {
            suspendCancellableCoroutine { continuation ->
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { result ->
                        val userUID = result.user?.uid ?: ""
                        continuation.resume(userUID)
                    }
                    .addOnFailureListener { exception ->
                        continuation.resumeWithException(exception)
                    }
            }
        } catch (e: Exception) {
            ""
        }
    }
}