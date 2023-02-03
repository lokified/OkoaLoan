package com.loki.okoaloan.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.loki.okoaloan.domain.model.User
import com.loki.okoaloan.domain.repository.FirebaseAccountRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAccountRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): FirebaseAccountRepository {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let {
                    User(id = it.uid, isAnonymous = it.isAnonymous)
                } ?: User())
            }

            auth.addAuthStateListener(listener)
            awaitClose {
                auth.removeAuthStateListener(listener)
            }
        }

    override suspend fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously().await()
    }

    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        if (auth.currentUser!!.isAnonymous) {
            auth.currentUser!!.delete()
        }

        auth.signOut()
    }
}